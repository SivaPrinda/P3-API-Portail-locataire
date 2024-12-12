package com.openclassrooms.P3_API_Portail_locataire.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record MessageDTO(
        @Schema(description = "Message")
        String message
) {
}
