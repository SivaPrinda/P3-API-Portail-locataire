package com.openclassrooms.P3_API_Portail_locataire.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

// This class is a Data Transfer Object (DTO) used to represent the data required for user registration.
public record RegisterUserDTO(

        @Schema(description = "User mail")
        String email,

        @Schema(description = "User name")
        String name,

        @Schema(description = "User password")
        String password
){}
