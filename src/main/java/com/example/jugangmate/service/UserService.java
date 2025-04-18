package com.example.jugangmate.service;

import com.example.jugangmate.JsonUtil;
import com.example.jugangmate.entity.User;
import com.example.jugangmate.form.UserForm;
import com.example.jugangmate.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JsonUtil jsonUtil;
    public User userSignup(UserForm userForm) { // TODO. 이메일 형식 검사, 중복체크 및 여러가지 예외처리

        String role = userForm.getUserRole();

        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .userRole(jsonUtil.RoleEnumConvert(role))
                .build();

        userRepository.save(user);

        return user;
    }
}
