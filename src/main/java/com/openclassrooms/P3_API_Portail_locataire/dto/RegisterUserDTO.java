package com.openclassrooms.P3_API_Portail_locataire.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterUserDTO(
        @Schema(description = "User email")
        String email,
        @Schema(description = "User name")
        String name,
        @Schema(description = "User password")
        String password
){}
