package com.example.jugangmate.service;

import com.example.jugangmate.*;
import com.example.jugangmate.entity.User;
import com.example.jugangmate.form.UserForm;
import com.example.jugangmate.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JsonUtil jsonUtil;

    private final JwtUtil jwtUtil;

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

    public User userLogin(UserForm userForm) { // TODO. JWK를 이용한 로그인 구현
        User user = userRepository.findByEmail(userForm.getEmail());

        if (user == null) {
            throw new CustomException(CustomResponseException.NON_EXISTENT_USER);
        }

        if (!passwordEncoder.matches(userForm.getPassword(), user.getPassword())) {
            throw new CustomException(CustomResponseException.WRONG_PWD);
        }


        return user;
    }

    public Map<String, Object> getJwks() {
        return jwtUtil.getJwks();
    }
}
