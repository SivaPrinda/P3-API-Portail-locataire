package com.openclassrooms.P3_API_Portail_locataire.services.impl;

import com.openclassrooms.P3_API_Portail_locataire.Exception.ResponseEntityException;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.RentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Rental;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import com.openclassrooms.P3_API_Portail_locataire.repositories.RentalRepository;
import com.openclassrooms.P3_API_Portail_locataire.services.IRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService implements IRentalService {

    private final RentalRepository rentalRepository;
    private final UserService userService;

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "Rental with id %d not found", id));
    }

    @Override
    public Rental addRental(CreateRentalDTO rentalDto) throws IOException {
        Rental rental = new Rental();
        rental.setName(rentalDto.name());
        rental.setDescription(rentalDto.description());
        rental.setSurface((int) rentalDto.surface());
        rental.setPrice(rentalDto.price());
        rental.setPicture(rentalDto.picture().getBytes());
        User owner = userService.getConnectedUser();
        rental.setOwner(owner);
        rental = rentalRepository.save(rental);
        return rental;
    }

    @Override
    public String updateRental(Long id, RentalDTO rentalDto) {
        Optional<Rental> rentalOpt = rentalRepository.findById(id);
        if (rentalOpt.isPresent()) {
            Rental rental = rentalOpt.get();
            rental.setName(rentalDto.name());
            rental.setDescription(rentalDto.description());
            rental.setSurface((int) rentalDto.surface());
            rental.setPrice(rentalDto.price());
            //rental.setPicture(String.valueOf(rentalDto.picture()));
            return rental.toString();
        } else {
            return null;
        }
    }
}
