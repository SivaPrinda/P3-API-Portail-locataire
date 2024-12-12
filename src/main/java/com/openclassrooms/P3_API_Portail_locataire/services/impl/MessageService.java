package com.openclassrooms.P3_API_Portail_locataire.services.impl;

import com.openclassrooms.P3_API_Portail_locataire.Exception.ResponseEntityException;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateMessageDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.RentalDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.UserDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Message;
import com.openclassrooms.P3_API_Portail_locataire.models.Rental;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import com.openclassrooms.P3_API_Portail_locataire.repositories.MessageRepository;
import com.openclassrooms.P3_API_Portail_locataire.repositories.RentalRepository;
import com.openclassrooms.P3_API_Portail_locataire.repositories.UserRepository;
import com.openclassrooms.P3_API_Portail_locataire.services.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final RentalRepository rentalRepository;
    private final RentalService rentalService;
    private final UserRepository userRepository;

    @Override
    public String saveMessage(CreateMessageDTO createMessageDto) {
        Message message = new Message();

        UserDTO user = userService.getUser(createMessageDto.userId());
        User connectedUser = userRepository.findById(user.id())
                .orElseThrow(() -> new ResponseEntityException(HttpStatus.UNAUTHORIZED, "User not found"));
        message.setUser(connectedUser);

        RentalDTO rental = rentalService.getRentalById(createMessageDto.rentalId());
        Rental selectedRental = rentalRepository.findById(rental.id())
                .orElseThrow(() -> new ResponseEntityException(HttpStatus.UNAUTHORIZED, "Rental not found"));
        message.setRental(selectedRental);

        message.setMessage(createMessageDto.message());

        message = messageRepository.save(message);
        return message.toString();
    }
}