package com.example.kakao.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.kakao._core.advice.ValidAdvice;
import com.example.kakao._core.config.FilterConfig;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao.core.MockData;
import com.example.kakao.product.option.Option;
import com.example.kakao.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Import({ ValidAdvice.class, FilterConfig.class }) // webmvc가 ioc에 올려주지 않는 것을 직접 로딩하기
@EnableAspectJAutoProxy // aop 활성화
@WebMvcTest(ProductRestController.class) // f - ds - uc
public class ProductRestControllerTest extends MockData{

    @Autowired
    private MockMvc mvc; // 컨트롤러 요청 객체

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper om;

    @Test
    public void findAll_test() throws Exception {
        // given
        String page = "0";
        String jwt = JwtTokenUtils.createMockUser();
        System.out.println("테스트 : "+jwt);

        // stub
        List<Product> productList = Arrays.asList(getProduct(1, "상품1"), getProduct(2, "상품2"));

        List<ProductResponse.FindAllDTO> responseDTOs = productList.stream()
            .map(p -> new ProductResponse.FindAllDTO(p))
            .collect(Collectors.toList());
        
        when(productService.findAll(anyInt())).thenReturn(responseDTOs);

        // when
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/products").param("page", page).header("Authorization", "Bearer "+jwt));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        // 검증 직접 하기
    }

    @Test
    public void findById_test() throws Exception{
        // given
        Integer id = 1;
        String jwt = JwtTokenUtils.createMockUser();

        // stub
        Product p1 = getProduct(1, "상품1");
        Option o1 = getOption(1, "옵션1", p1);
        Option o2 = getOption(2, "옵션2", p1);
        p1.addOption(o1);
        p1.addOption(o2);

        ProductResponse.FindByIdDTO responseDTO = new ProductResponse.FindByIdDTO(p1);
        when(productService.findById(anyInt())).thenReturn(responseDTO);

        // when
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/products/"+id).header("Authorization", "Bearer "+jwt));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
    }

}
