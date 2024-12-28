package com.openclassrooms.P3_API_Portail_locataire.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

// This class is a Data Transfer Object (DTO) used to represent a message in a response.
public record MessageDTO(
        @Schema(description = "Message")
        String message
) {
}
