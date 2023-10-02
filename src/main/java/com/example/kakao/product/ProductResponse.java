package com.example.kakao.product;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Column;

import com.example.kakao.product.option.Option;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProductResponse {

    // (기능1) 상품 목록보기
    @ToString
    @Getter
    @Setter
    public static class FindAllDTO {
        private Integer id;
        private String productName;
        private String image;
        private Integer price;

        public FindAllDTO(Product product) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.image = product.getImage();
            this.price = product.getPrice();
        }
    }

    // (기능2) 상품 상세보기
    @Getter
    @Setter
    public static class FindByIdDTO {
        private Integer productId;
        private String productName;
        private String productImage;
        private Integer productPrice;
        private Integer productStartCount;
        private List<OptionDTO> options;

        public FindByIdDTO(Product product) {
            this.productId = product.getId();
            this.productName = product.getProductName();
            this.productImage = product.getImage();
            this.productPrice = product.getPrice();
            this.productStartCount = 5;
            this.options = product.getOptions().stream()
                    .map(o -> new OptionDTO(o))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        class OptionDTO {
            private Integer optionId;
            private String optionName;
            private Integer optionPrice;

            OptionDTO(Option option) {
                this.optionId = option.getId();
                this.optionName = option.getOptionName();
                this.optionPrice = option.getPrice();
            }
        }
    }

    // 상품조회 + 옵션조회
    @Getter
    @Setter
    public static class FindByIdV1DTO {
        private Integer productId;
        private String productName;
        private String productImage;
        private Integer productPrice;
        private Integer productStartCount;
        private List<OptionDTO> options;

        public FindByIdV1DTO(Product product, List<Option> options) {
            this.productId = product.getId();
            this.productName = product.getProductName();
            this.productImage = product.getImage();
            this.productPrice = product.getPrice();
            this.productStartCount = 5;
            this.options = options.stream()
                    .map(o -> new OptionDTO(o))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        class OptionDTO {
            private Integer optionId;
            private String optionName;
            private Integer optionPrice;

            OptionDTO(Option option) {
                this.optionId = option.getId();
                this.optionName = option.getOptionName();
                this.optionPrice = option.getPrice();
            }
        }
    }

    // 양방향 매핑
    @Getter
    @Setter
    public static class FindByIdV2DTO {
        private Integer productId;
        private String productName;
        private String productImage;
        private Integer productPrice;
        private Integer productStartCount;
        private List<OptionDTO> options;

        public FindByIdV2DTO(Product product) {
            this.productId = product.getId();
            this.productName = product.getProductName();
            this.productImage = product.getImage();
            this.productPrice = product.getPrice();
            this.productStartCount = 5;
            System.out.println("이제 Lazy Loading 한다 =================");
            this.options = product.getOptions().stream()
                    .map(o -> new OptionDTO(o))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        class OptionDTO {
            private Integer optionId;
            private String optionName;
            private Integer optionPrice;

            OptionDTO(Option option) {
                System.out.println("이제 Lazy Loading 시작됨 =========");
                this.optionId = option.getId();
                this.optionName = option.getOptionName();
                this.optionPrice = option.getPrice();
            }
        }
    }

    // 옵션만 조회
    @Getter
    @Setter
    public static class FindByIdV3DTO {
        private Integer productId;
        private String productName;
        private String productImage;
        private Integer productPrice;
        private Integer productStartCount;
        private List<OptionDTO> options;

        public FindByIdV3DTO(List<Option> options) {
            System.out.println("이제 Lazy 시작한다???????????????????????");
            this.productId = options.get(0).getProduct().getId();
            this.productName = options.get(0).getProduct().getProductName();
            this.productImage = options.get(0).getProduct().getImage();
            this.productPrice = options.get(0).getProduct().getPrice();
            this.productStartCount = 5;
            this.options = options.stream()
                    .map(o -> new OptionDTO(o))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        class OptionDTO {
            private Integer optionId;
            private String optionName;
            private Integer optionPrice;

            OptionDTO(Option option) {
                this.optionId = option.getId();
                this.optionName = option.getOptionName();
                this.optionPrice = option.getPrice();
            }
        }
    }
}