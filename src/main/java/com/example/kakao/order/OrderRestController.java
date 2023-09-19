package com.example.kakao.order;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakao._core.errors.exception.Exception401;
import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderRestController {

    private final HttpSession session;
    private OrderService orderService;

    // 결재하기 - (주문 인서트)
    @PostMapping("/orders/save")
    public ResponseEntity<?> save() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new Exception401("인증되지 않았습니다");
        }
        orderService.saveOrder(sessionUser);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // (기능5) 주문결과 확인
    @GetMapping("/orders/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return null;
    }

    // (기능4) 주문상품 정보조회 (유저별) - 장바구니 내역 가져오기
    @GetMapping("/orders")
    public ResponseEntity<?> findAllByUser() {
        return null;
    }

}
