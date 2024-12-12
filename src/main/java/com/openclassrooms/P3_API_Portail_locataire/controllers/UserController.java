package com.openclassrooms.P3_API_Portail_locataire.controllers;

import com.openclassrooms.P3_API_Portail_locataire.dto.response.UserDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import com.openclassrooms.P3_API_Portail_locataire.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(iUserService.getUser(id));
    }

}
