package com.example.kakao.core.utils;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao.user.User;

public class JwtTokenUtilsTest {
    
    @Test
    public void jwt_create_test(){
        User user = User.builder().id(1).email("ssar@nate.com").build();
        String jwt = JwtTokenUtils.create(user);
        System.out.println(jwt);
    }

    @Test
    public void jwt_verify_test(){
        String jwt = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJtZXRhY29kaW5nLWtleSIsImlkIjoxLCJlbWFpbCI6InNzYXJAbmF0ZS5jb20iLCJleHAiOjE2OTYyMjQ2MDV9.qTQ2oW_O5T_W_SmDbx2WAfAGlvsVC6pn4KHk7tJTltKUMCdBf7btDnr1wepxdw334iQF-Jxt3KcaDuiM46D8Lg";
        DecodedJWT decodedJWT = JwtTokenUtils.verify(jwt);

        int id = decodedJWT.getClaim("id").asInt();
        String email = decodedJWT.getClaim("email").asString();

        System.out.println("id : "+id);
        System.out.println("email : "+email);
    }
}
