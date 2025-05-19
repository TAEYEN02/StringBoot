package com.korea.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.board.dto.UserRequestDTO;
import com.korea.board.model.UserEntity;
import com.korea.board.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String signup(UserRequestDTO dto) {
        boolean exists = userRepository.findByUsername(dto.getUsername()).isPresent();
        if (exists) {
            return "이미 존재하는 아이디입니다.";
        }

        UserEntity user = UserEntity.builder()
                .username(dto.getUsername())
                .password(dto.getPassword()) // TODO: 추후 암호화 (BCrypt 등)
                .build();

        userRepository.save(user);
        return "회원가입 성공";
    }
    
    public String login(UserRequestDTO dto) {
        return userRepository.findByUsername(dto.getUsername())
            .map(user -> {
                if (user.getPassword().equals(dto.getPassword())) {
                    return "로그인 성공";
                } else {
                    return "비밀번호가 일치하지 않습니다.";
                }
            })
            .orElse("존재하지 않는 아이디입니다.");
    }
}
