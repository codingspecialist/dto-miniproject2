package com.example.kakao.product.option;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OptionJPARepository extends JpaRepository<Option, Integer> {

    @Query("select o from Option o where o.product.id = :id")
    List<Option> findByProductId(@Param("id") Integer id);
}
