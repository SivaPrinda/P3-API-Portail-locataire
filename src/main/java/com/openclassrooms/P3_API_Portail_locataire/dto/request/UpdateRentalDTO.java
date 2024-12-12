package com.openclassrooms.P3_API_Portail_locataire.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateRentalDTO(
        @Schema(description = "Rental name")
        String name,
        @Schema(description = "Rental address")
        double surface,
        @Schema(description = "Rental price")
        double price,
        @Schema(description = "Rental description")
        String description
) {
}
