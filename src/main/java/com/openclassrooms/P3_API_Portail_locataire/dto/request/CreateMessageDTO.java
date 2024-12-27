package com.openclassrooms.P3_API_Portail_locataire.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

// This class is a Data Transfer Object (DTO) used to create a message.
public record CreateMessageDTO(

    @Schema(description = "Message")
    String message,

    // Maps the 'userId' field to 'user_id' in JSON for consistency
    @Schema(description = "User ID")
    @JsonProperty("user_id")
    Long userId,

    // Maps the 'rentalId' field to 'rental_id' in JSON for consistency
    @Schema(description = "Rental ID")
    @JsonProperty("rental_id")
    Long rentalId
)
{}
