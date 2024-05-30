package com.example.ecommercebe.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        return principal.getAttributes();
    }
    @GetMapping("/oauth2/authorization/github")
    public ResponseEntity test(){
        return ResponseEntity.ok("ok");
    }
    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

            // Lấy thông tin người dùng từ OAuth2User
//            String username = oauth2User.getAttribute("name");
//            String avatarUrl = oauth2User.getAttribute("avatar_url");
            // Và các thông tin khác

            return oauth2User.toString();
        } else {
            return "Đăng nhập thất bại!";
        }
    }
    @GetMapping("/login-failure")
    public String loginFail() {
        return "Đăng nhập thất bại!";
    }
}
