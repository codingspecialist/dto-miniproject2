package com.example.kakao.option;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.kakao.product.Product;
import com.example.kakao.product.option.Option;
import com.example.kakao.product.option.OptionJPARepository;

@DataJpaTest
public class OptionJPARepositoryTest {
    
    @Autowired
    private OptionJPARepository optionJPARepository;

    @Test
    public void findByProductId_test(){
        List<Option> options = optionJPARepository.findByProductId(1);
        // Lazy Loading 발동
        System.out.println("lazy 발동======================");
        String p1 = options.get(0).getProduct().getProductName();
        System.out.println(p1);
    }
}
