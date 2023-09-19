package com.example.kakao.user;

import javax.servlet.http.HttpSession;


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

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO requestDTO) {
        userService.join(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO) {
        User sessionUser = userService.login(requestDTO);
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        session.invalidate();
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }
}
