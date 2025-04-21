package com.example.jugangmate.service;

import com.example.jugangmate.CustomException;
import com.example.jugangmate.CustomResponseException;
import com.example.jugangmate.JsonUtil;
import com.example.jugangmate.RoleEnum;
import com.example.jugangmate.entity.User;
import com.example.jugangmate.form.UserForm;
import com.example.jugangmate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JsonUtil jsonUtil;

    public User userSignup(UserForm userForm) {
        RoleEnum userRole = jsonUtil.RoleEnumConvert(userForm.getUserRole());
        User testUser = userRepository.findByEmail(userForm.getEmail());

        if(testUser != null) {
            throw new CustomException(CustomResponseException.DUPLICATE_EMAIL);
        }

        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .userRole(userRole)
                .build();

        userRepository.save(user);

        return user;
    }
}
