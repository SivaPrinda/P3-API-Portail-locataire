package com.openclassrooms.P3_API_Portail_locataire.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

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
    MultipartFile picture,
    @Schema(description = "Rental description")
    String description,
    @Schema(description = "Rental owner")
    Integer owner_id

){}
