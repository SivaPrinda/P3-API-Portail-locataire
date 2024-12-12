package com.openclassrooms.P3_API_Portail_locataire.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public record UserDTO(
        @Schema(description = "User id")
        Long id,
        @Schema(description = "User name")
        String name,
        @Schema(description = "User email")
        String mail,
        @Schema(description = "User creation date")
        @JsonProperty("created_at")
        Instant createdAt,
        @Schema(description = "User update date")
        @JsonProperty("updated_at")
        Instant updatedAt
){}
