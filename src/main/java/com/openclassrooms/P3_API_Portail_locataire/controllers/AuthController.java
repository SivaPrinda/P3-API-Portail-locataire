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

    /**
     * Registers a new user.
     * Endpoint: POST /api/auth/register
     *
     * @param registerUser the user details sent in the request body.
     * @return a ResponseEntity containing a TokenDTO (the authentication token).
     */
    @PostMapping("/register")
    public ResponseEntity<TokenDTO> signIn(@RequestBody RegisterUserDTO registerUser) {
        return ResponseEntity.ok(new TokenDTO(iUserService.register(registerUser)));
    }

    /**
     * Logs in an existing user.
     * Endpoint: POST /api/auth/login
     *
     * @param loginUser the login credentials sent in the request body.
     * @return a ResponseEntity containing a TokenDTO (the authentication token).
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> signUp(@RequestBody LoginUserDTO loginUser) {
        return ResponseEntity.ok(new TokenDTO(iUserService.login(loginUser)));
    }

    /**
     * Retrieves the currently logged-in user's information.
     * Endpoint: GET /api/auth/me
     *
     * @return a ResponseEntity containing a UserDTO (user details).
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getConnectedUser() {
        return ResponseEntity.ok(mapToUserDTO(iUserService.getConnectedUser()));
    }

    /**
     * Maps a User object to a UserDTO object.
     * This is used to expose user data in a structured and secure format.
     *
     * @param user the User object to be mapped.
     * @return a UserDTO object containing user information.
     */
    private UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
