package com.openclassrooms.P3_API_Portail_locataire.controllers;

import com.openclassrooms.P3_API_Portail_locataire.services.IPictureService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pictures")
@RequiredArgsConstructor
public class PictureController {

    private final IPictureService pictureService;

    /**
     * Retrieve a picture by its ID.
     * Endpoint: GET /api/pictures/{id}
     * Produces: JPEG or PNG image.
     *
     * @param id the ID of the picture to retrieve.
     * @return a byte array representing the picture file.
     */
    @Operation(
        summary = "Get picture",
        description = "Retrieve picture by id"
    )
    @GetMapping(value = "/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] getPicture(@PathVariable String id) {
        return pictureService.getPicture(id);
    }

}