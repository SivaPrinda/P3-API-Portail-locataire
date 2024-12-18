package com.openclassrooms.P3_API_Portail_locataire.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginUserDTO(
        @Schema(description = "User mail")
        String email,
        @Schema(description = "User password")
        String password
){}