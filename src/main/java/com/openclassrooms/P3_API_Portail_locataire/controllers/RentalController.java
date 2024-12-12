package com.openclassrooms.P3_API_Portail_locataire.controllers;

import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.UpdateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.ListRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.MessageDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.RentalDTO;
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

    @PostMapping(value="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageDTO> createRental(@ModelAttribute CreateRentalDTO rentalRequestDTO) throws IOException {

        iRentalService.addRental(rentalRequestDTO);
        return ResponseEntity.ok(new MessageDTO("Rental created !"));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RentalDTO getRental(@PathVariable("id") final Long id) {
        return iRentalService.getRentalById(id);
    }


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ListRentalDTO> getRentals() {
       return ResponseEntity.ok(new ListRentalDTO(iRentalService.getAllRentals()));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageDTO> updateRental(@PathVariable("id") final Long id, @ModelAttribute UpdateRentalDTO rentalDto) {
        iRentalService.updateRental(id, rentalDto);
            return ResponseEntity.ok(new MessageDTO("Rental update !"));
    }

}
