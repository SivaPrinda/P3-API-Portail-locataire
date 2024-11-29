package com.openclassrooms.P3_API_Portail_locataire.services.impl;

import com.openclassrooms.P3_API_Portail_locataire.Exception.ResponseEntityException;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateRentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.RentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Rental;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import com.openclassrooms.P3_API_Portail_locataire.repositories.RentalRepository;
import com.openclassrooms.P3_API_Portail_locataire.services.IPictureService;
import com.openclassrooms.P3_API_Portail_locataire.services.IRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService implements IRentalService {

    private final RentalRepository rentalRepository;
    private final UserService userService;
    private final IPictureService pictureService;

    @Override
    public List<RentalDTO> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();

        // Convert each Rental entity to RentalDTO with raw image data
        return rentals.stream().map(rental -> new RentalDTO(
               rental.getId(),
               rental.getName(),
               rental.getSurface(),
               rental.getPrice(),
               rental.getPicture(),
               rental.getDescription(),
               rental.getOwner().getId()
       )).collect(Collectors.toList());
    }

    @Override
    public RentalDTO getRentalById(Long id) {
        //return rentalRepository.findById(id).orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "Rental with id %d not found", id));
        Rental rental = rentalRepository.findById(id).orElseThrow(() ->
                new ResponseEntityException(HttpStatus.NOT_FOUND, "Rental with id %d not found".formatted(id))
        );

        return new RentalDTO(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getOwner().getId()
        );


    }

    @Override
    public Rental addRental(CreateRentalDTO rentalDto){
        Rental rental = new Rental();
        rental.setName(rentalDto.name());
        rental.setDescription(rentalDto.description());
        rental.setSurface((int) rentalDto.surface());
        rental.setPrice(rentalDto.price());
        rental.setPicture(pictureService.savePicture(rentalDto.picture()));
        User owner = userService.getConnectedUser();
        rental.setOwner(owner);
        rental = rentalRepository.save(rental);
        return rental;
    }

    @Override
    public String updateRental(Long id, CreateRentalDTO rentalDto) {
        Optional<Rental> rentalOpt = rentalRepository.findById(id);
        if (rentalOpt.isPresent()) {
            Rental rental = rentalOpt.get();
            rental.setName(rentalDto.name());
            rental.setDescription(rentalDto.description());
            rental.setSurface((int) rentalDto.surface());
            rental.setPrice(rentalDto.price());
            rental.setPicture(pictureService.savePicture(rentalDto.picture()));
            return rental.toString();
        } else {
            return null;
        }
    }
}
