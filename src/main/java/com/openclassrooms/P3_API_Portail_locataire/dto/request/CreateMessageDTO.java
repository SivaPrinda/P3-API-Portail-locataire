package com.openclassrooms.P3_API_Portail_locataire.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateMessageDTO(
    @Schema(description = "message")
    String message,
    @JsonProperty("user_id")
    Long userId,
    @JsonProperty("rental_id")
    Long rentalId
)
{}
