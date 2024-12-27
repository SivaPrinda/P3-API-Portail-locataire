package com.openclassrooms.P3_API_Portail_locataire.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

// This class is a Data Transfer Object (DTO) used to represent a rental properties in a response.
public record RentalDTO(

    @Schema(description = "Rental id")
    Long id,

    @Schema(description = "Rental name")
    String name,

    @Schema(description = "Rental address")
    double surface,

    @Schema(description = "Rental price")
    double price,

    @Schema(description = "Rental picture")
    String picture,

    @Schema(description = "Rental description")
    String description,

    @Schema(description = "Rental creation date")
    @JsonProperty("created_at")
    LocalDateTime created_at,

    @Schema(description = "Rental update date")
    @JsonProperty("updated_at")
    LocalDateTime updatedAt,

    @Schema(description = "Rental owner")
    @JsonProperty("owner_id")
    Long ownerId

){}
