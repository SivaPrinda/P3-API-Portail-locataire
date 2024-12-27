package com.openclassrooms.P3_API_Portail_locataire.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

// This class is a Data Transfer Object (DTO) used to represent a list of rental properties in a response.
public record ListRentalDTO(
    @Schema(description = "List of rentals")
    List<RentalDTO> rentals
) {
}