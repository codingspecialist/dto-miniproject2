package com.example.kakao.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakao._core.utils.ApiUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/test")
    public String test(HttpServletResponse response){
        session.setAttribute("hello", "hello");
        Cookie cookie = new Cookie("name", "ssar");
        cookie.setHttpOnly(false);
        response.setStatus(200);
        response.addCookie(cookie);
        // email=cos
        return "ok";
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO requestDTO, Errors errors) {
        userService.join(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO requestDTO, Errors errors) {
        String jwt = userService.login(requestDTO);
        return ResponseEntity.ok().header("Authorization", "Bearer "+jwt).body(ApiUtils.success(null));
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        session.invalidate();
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }
}
