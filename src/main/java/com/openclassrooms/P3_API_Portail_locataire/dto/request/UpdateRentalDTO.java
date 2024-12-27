package com.openclassrooms.P3_API_Portail_locataire.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

// This class is a Data Transfer Object (DTO) used to represent the data required to update an existing rental.
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
