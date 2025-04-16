package com.example.jugangmate.controller;

import com.example.jugangmate.entity.User;
import com.example.jugangmate.form.UserForm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class UserController {
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
