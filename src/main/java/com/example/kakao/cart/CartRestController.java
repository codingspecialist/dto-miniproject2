package com.example.kakao.cart;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakao._core.errors.exception.Exception401;
import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CartRestController {

    private final CartService cartService;
    private final HttpSession session;

    // (기능3) 장바구니 조회
    @GetMapping("/carts")
    public ResponseEntity<?> findAllByUser() {
        return null;
    }

    // 장바구니 담기
    @PostMapping("/carts/add")
    public ResponseEntity<?> addCartList(@RequestBody List<CartRequest.SaveDTO> requestDTOs) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new Exception401("인증되지 않았습니다");
        }
        cartService.addCartList(requestDTOs, sessionUser);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // 주문하기 (안해도됨 굳이!!)
    @PostMapping("/carts/update")
    public ResponseEntity<?> update(@RequestBody List<CartRequest.UpdateDTO> requestDTOs) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new Exception401("인증되지 않았습니다");
        }
        cartService.update(requestDTOs, sessionUser);
        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
