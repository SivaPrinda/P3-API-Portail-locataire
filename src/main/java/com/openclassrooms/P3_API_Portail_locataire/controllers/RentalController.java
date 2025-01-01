package com.openclassrooms.P3_API_Portail_locataire.controllers;

import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.UpdateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.ListRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.MessageDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.RentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Rental;
import com.openclassrooms.P3_API_Portail_locataire.services.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private IRentalService iRentalService;

    /**
     * Creates a new rental.
     * Endpoint: POST /api/rentals
     * Consumes: multipart/form-data (e.g., files and form data).
     *
     * @param rentalRequestDTO the details of the rental sent in the request body.
     * @return a ResponseEntity containing a success message.
     * @throws IOException if an error occurs while processing files.
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageDTO> createRental(@ModelAttribute CreateRentalDTO rentalRequestDTO) throws IOException {

        iRentalService.addRental(rentalRequestDTO);
        return ResponseEntity.ok(new MessageDTO("Rental created !"));
    }

    /**
     * Retrieves a rental by its ID.
     * Endpoint: GET /api/rentals/{id}
     *
     * @param id the ID of the rental to retrieve.
     * @return a RentalDTO object with the rental details.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RentalDTO getRental(@PathVariable("id") final Long id) {
        return mapToRentalDTO(iRentalService.getRentalById(id));
    }


    /**
     * Retrieves a list of all rentals.
     * Endpoint: GET /api/rentals
     *
     * @return a ResponseEntity containing a ListRentalDTO with all rentals.
     */
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ListRentalDTO> getRentals() {
       return ResponseEntity.ok(new ListRentalDTO(iRentalService.getAllRentals()));
    }

    /**
     * Updates a rental by its ID.
     * Endpoint: PUT /api/rentals/{id}
     * Consumes: multipart/form-data (e.g., files and form data).
     *
     * @param id the ID of the rental to update.
     * @param rentalDto the updated rental data sent in the request body.
     * @return a ResponseEntity containing a success message.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageDTO> updateRental(@PathVariable("id") final Long id, @ModelAttribute UpdateRentalDTO rentalDto) {
        iRentalService.updateRental(id, rentalDto);
            return ResponseEntity.ok(new MessageDTO("Rental update !"));
    }

    /**
     * Converts a Rental entity to a RentalDTO object.
     * Used to expose rental data in a structured and safe format.
     *
     * @param rental the Rental object to map.
     * @return a RentalDTO object containing rental details.
     */
    private RentalDTO mapToRentalDTO(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getCreatedAt(),
                rental.getUpdatedAt(),
                rental.getOwner().getId()
        );
    }
}
