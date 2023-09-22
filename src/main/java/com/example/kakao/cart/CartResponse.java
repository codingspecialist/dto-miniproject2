package com.example.kakao.cart;

import java.util.List;
import java.util.stream.Collectors;

import com.example.kakao.product.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class CartResponse {

    // (기능3) 장바구니 조회
    // 내가 지금 디비에서 조회한 Entity가 머지?
    @ToString
    @Getter
    @Setter
    public static class FindAllByUserDTO {
        private Integer totalPrice;
        private List<ProductDTO> products;

        public FindAllByUserDTO(List<Cart> cartList) {
            this.totalPrice = cartList.stream().mapToInt(cart -> cart.getPrice()).sum();
            this.products = cartList.stream()
                    .map(cart -> cart.getOption().getProduct()).distinct()
                    .map(product -> new ProductDTO(product, cartList))
                    .collect(Collectors.toList());
        }

        @Getter @Setter
        class ProductDTO {
            private String productName;
            private List<CartDTO> carts;

            public ProductDTO(Product product, List<Cart> carts) {
                this.productName = product.getProductName();
                this.carts = carts.stream()
                                .filter(cart -> cart.getOption().getProduct().getId() == product.getId())
                                .map(cart -> new CartDTO(cart))
                                .collect(Collectors.toList());
            }

            @Getter @Setter
            class CartDTO {
                private String cartOptionName;
                private Integer cartQuantity;
                private Integer cartPrice;

                public CartDTO(Cart cart) {
                    this.cartOptionName = cart.getOption().getOptionName();
                    this.cartQuantity = cart.getQuantity();
                    this.cartPrice = cart.getPrice();
                }

                
            }
        }
    }
}
