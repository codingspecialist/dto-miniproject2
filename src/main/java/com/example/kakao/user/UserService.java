package com.example.kakao.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception500;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public void join(UserRequest.JoinDTO requestDTO) {
        try {
            userJPARepository.save(requestDTO.toEntity());
        } catch (Exception e) {
            throw new Exception500("unknown server error");
        }
    }

    public User login(UserRequest.LoginDTO requestDTO) {
        User userPS = userJPARepository.findByEmail(requestDTO.getEmail())
            .orElseThrow(()-> new Exception400("email을 찾을 수 없습니다 : "+requestDTO.getEmail()));
        return userPS;
    }
}
