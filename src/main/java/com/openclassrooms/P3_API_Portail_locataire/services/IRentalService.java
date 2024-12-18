package com.openclassrooms.P3_API_Portail_locataire.services;

import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.UpdateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.RentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Rental;

import java.io.IOException;
import java.util.List;

public interface IRentalService {

    List<RentalDTO> getAllRentals();
    Rental getRentalById(Long id);
    String addRental(CreateRentalDTO rentalDto) throws IOException;
    String updateRental(Long id, UpdateRentalDTO rentalDto);

}
