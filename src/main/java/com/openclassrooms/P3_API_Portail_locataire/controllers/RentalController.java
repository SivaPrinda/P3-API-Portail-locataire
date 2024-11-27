package com.openclassrooms.P3_API_Portail_locataire.controllers;

import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.RentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Rental;
import com.openclassrooms.P3_API_Portail_locataire.services.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private IRentalService iRentalService;

    @PostMapping(value="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createRental(@ModelAttribute CreateRentalDTO rentalRequestDTO) throws IOException {
        iRentalService.addRental(rentalRequestDTO);
        return ResponseEntity.ok("Rental Created");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Rental getRental(@PathVariable("id") final Long id) {
        return iRentalService.getRentalById(id);
    }


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Rental> getRentals() {
        return iRentalService.getAllRentals();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateRental(@PathVariable("id") final Long id, @ModelAttribute RentalDTO rentalDto) {
        try {
            String updatedRental = iRentalService.updateRental(id, rentalDto);
            if (updatedRental != null) {
                return new ResponseEntity<>(Collections.singletonMap("message", "Rental updated !"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Collections.singletonMap("error", "Rental not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Error while updating rental: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
