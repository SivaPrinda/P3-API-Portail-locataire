package com.openclassrooms.P3_API_Portail_locataire.controllers;

import com.openclassrooms.P3_API_Portail_locataire.dto.LoginUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.RegisterUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import com.openclassrooms.P3_API_Portail_locataire.services.UserService;
import com.openclassrooms.P3_API_Portail_locataire.services.impl.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    private JWTService jwtService;

    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> signIn(@RequestBody RegisterUserDTO registerUser) {
        User userRegister = new User();
        userRegister.setPassword(registerUser.password());
        userRegister.setEmail(registerUser.email());
        userRegister.setName(registerUser.name());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public String signUp(@RequestBody LoginUserDTO loginUser) {
        userService.loginUser(loginUser.email(), loginUser.password());
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser.email(), loginUser.password());
        return jwtService.generateToken(authentication);
    }

}
