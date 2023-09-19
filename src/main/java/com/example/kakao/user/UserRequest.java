package com.example.kakao.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class UserRequest {
    @Getter
    @Setter
    @ToString
    public static class JoinDTO {
        private String email;
        private String password;
        private String username;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .username(username)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class LoginDTO {
        private String email;
        private String password;
    }
}
