package com.openclassrooms.P3_API_Portail_locataire.controllers;

import com.openclassrooms.P3_API_Portail_locataire.dto.request.LoginUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.RegisterUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.TokenDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.UserDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import com.openclassrooms.P3_API_Portail_locataire.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> signIn(@RequestBody RegisterUserDTO registerUser) {
        return ResponseEntity.ok(new TokenDTO(iUserService.register(registerUser)));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> signUp(@RequestBody LoginUserDTO loginUser) {
        return ResponseEntity.ok(new TokenDTO(iUserService.login(loginUser)));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getConnectedUser() {
        return ResponseEntity.ok(iUserService.getConnectedUser());
    }
}
