package com.example.ecommercebe.controller;


import com.example.ecommercebe.models.response.JwtResponse;
import com.example.ecommercebe.security.jwt.JwtUtils;
import com.example.ecommercebe.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
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
    public ResponseEntity<?> loginSuccess(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

            try {
                String jwt = jwtUtils.generateJwtOAuth2Token(authentication);

                List<String> roles = List.of("ROLE_USER");

                return ResponseEntity.ok(new JwtResponse(jwt,
                        Long.parseLong((Boolean.TRUE.equals(oauth2User.getAttribute("id"))) ? Objects.requireNonNull(oauth2User.getAttribute("id")) : "0"),
                        oauth2User.getAttribute("name"),
                        Boolean.TRUE.equals(oauth2User.getAttribute("email")) ? oauth2User.getAttribute("email") : oauth2User.getAttribute("login"),
                        roles));
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot generate token");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authentication failed");
        }
    }
    @GetMapping("/login-failure")
    public ResponseEntity<String> loginFail() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UserName or PassWord wrong");
    }
}
