package com.openclassrooms.P3_API_Portail_locataire.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenDTO(
    @Schema(description = "Token used to authenticate user")
    String token
) {
}