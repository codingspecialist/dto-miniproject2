package com.example.kakao._core.utils;

import java.time.Instant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.kakao.user.User;

public class JwtTokenUtils {

    public static String create(User user) {
        String jwt = JWT.create()
                        .withSubject("metacoding-key")
                        .withClaim("id", user.getId())
                        .withClaim("email", user.getEmail())
                        .withExpiresAt(Instant.now().plusMillis(1000*60*60*24*7L))
                        .sign(Algorithm.HMAC512("meta"));      
        return jwt;
    }

        public static String createMockUser() {
        String jwt = JWT.create()
                        .withSubject("metacoding-key")
                        .withClaim("id", 1)
                        .withClaim("email", "ssar@nate.com")
                        .withExpiresAt(Instant.now().plusMillis(1000*60*60*24*7L))
                        .sign(Algorithm.HMAC512("meta"));      
        return jwt;
    }

    public static DecodedJWT verify(String jwt)
            throws SignatureVerificationException, TokenExpiredException {
        jwt = jwt.replace("Bearer ", "");

        // JWT를 검증한 후, 검증이 완료되면, header, payload를 base64로 복호화함.
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("meta"))
                .build().verify(jwt);
        return decodedJWT;
    }

}