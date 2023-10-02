package com.example.kakao.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.product.option.Option;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductService productService; // 자바에서 final 변수는 반드시 초기화되어야 함.

    // (기능1) 상품 목록보기
    @GetMapping("/products")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        System.out.println("테스트 : findAll()");
        List<ProductResponse.FindAllDTO> responseDTOs = productService.findAll(page);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTOs));
    }

    // (기능2) 상품 상세보기
    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        ProductResponse.FindByIdDTO responseDTO = productService.findById(id);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 상품조회 + 옵션조회
    @GetMapping("/products/{id}/v1")
    public ResponseEntity<?> findByIdV1(@PathVariable int id) {
        ProductResponse.FindByIdV1DTO responseDTO = productService.findByIdV1(id);
        return ResponseEntity.ok(responseDTO);
    }

    // 상품조회 양방향 매핑
    @GetMapping("/products/{id}/v2")
    public ResponseEntity<?> findByIdV2(@PathVariable int id) {
        ProductResponse.FindByIdV2DTO responseDTO = productService.findByIdV2(id);
        return ResponseEntity.ok(responseDTO);
    }

    // 옵션조회
    @GetMapping("/products/{id}/v3")
    public ResponseEntity<?> findByIdV3(@PathVariable int id) {
        ProductResponse.FindByIdV3DTO responseDTO = productService.findByIdV3(id);
        return ResponseEntity.ok(responseDTO);
    }

    // 옵션조회
    @GetMapping("/products/{id}/v4")
    public ResponseEntity<?> findByIdV4(@PathVariable int id) {
        List<Option> responseDTO = productService.findByIdV4(id);
        return ResponseEntity.ok(responseDTO);
    }
}