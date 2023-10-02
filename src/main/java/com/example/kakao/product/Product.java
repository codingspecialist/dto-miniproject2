package com.example.kakao.product;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.example.kakao.product.option.Option;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="product_tb")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String productName;
    @Column(length = 1000, nullable = false)
    private String description;
    @Column(length = 500)
    private String image;
    private int price; // 톡딜가

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Option> options = new ArrayList<>();

    public void addOption(Option option){
        options.add(option);
    }

    @Builder
    public Product(int id, String productName, String description, String image, int price) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.price = price;
    }
}
