package com.openclassrooms.P3_API_Portail_locataire.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ListRentalDTO(
    @Schema(description = "List of rentals")
    List<RentalDTO> rentals
) {
}