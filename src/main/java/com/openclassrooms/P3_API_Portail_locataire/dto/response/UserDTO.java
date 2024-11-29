package com.openclassrooms.P3_API_Portail_locataire.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UserDTO(
        @Schema(description = "User id")
        Integer id,
        @Schema(description = "User email")
        String email,
        @Schema(description = "User name")
        String name,
        @Schema(description = "User creation date")
        LocalDateTime createdAt,
        @Schema(description = "User update date")
        LocalDateTime updatedAt
){}
