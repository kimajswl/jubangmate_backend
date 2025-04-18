package com.example.jugangmate.controller;

import com.example.jugangmate.entity.User;
import com.example.jugangmate.form.UserForm;
import com.example.jugangmate.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(UserForm userForm) {
        try {
            User user = userService.userSignup(userForm);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/master/login")
    public ResponseEntity<?> masterLogin(UserForm userForm) {
        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/chef/login")
    public ResponseEntity<?> chefLogin(UserForm userForm) {
        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/guest/login")
    public ResponseEntity<?> guestLogin(UserForm userForm) {
        return ResponseEntity.ok("로그인 성공");
    }
}
