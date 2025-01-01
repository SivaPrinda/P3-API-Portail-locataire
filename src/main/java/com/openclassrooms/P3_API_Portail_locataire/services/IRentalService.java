package com.openclassrooms.P3_API_Portail_locataire.services;

import com.openclassrooms.P3_API_Portail_locataire.Exception.ResponseEntityException;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.UpdateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.RentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Rental;

import java.io.IOException;
import java.util.List;

public interface IRentalService {

    /**
     * Retrieves all rentals from the database and converts them to RentalDTO objects.
     *
     * @return A list of RentalDTO objects representing all rentals.
     */
    List<RentalDTO> getAllRentals();

    /**
     * Retrieves a rental by its ID.
     *
     * @param id The ID of the rental.
     * @return The Rental entity.
     * @throws ResponseEntityException If the rental is not found.
     */
    Rental getRentalById(Long id);

    /**
     * Adds a new rental.
     *
     * @param rentalDto Data transfer object containing rental creation details.
     * @return A string representation of the created rental.
     */
    String addRental(CreateRentalDTO rentalDto) throws IOException;

    /**
     * Updates an existing rental.
     *
     * @param id        The ID of the rental to update.
     * @param rentalDto Data transfer object containing updated rental details.
     * @return A string representation of the updated rental, or null if not found.
     */
    String updateRental(Long id, UpdateRentalDTO rentalDto);

}
