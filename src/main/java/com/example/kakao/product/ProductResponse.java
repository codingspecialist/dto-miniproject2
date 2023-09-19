package com.example.kakao.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProductResponse {

    // (기능1) 상품 목록보기
    @ToString
    @Getter
    @Setter
    public static class FindAllDTO {

    }

    // (기능2) 상품 상세보기
    @Getter
    @Setter
    public static class FindByIdDTO {

    }
}