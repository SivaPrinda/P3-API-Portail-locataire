package com.openclassrooms.P3_API_Portail_locataire.services.impl;

import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateMessageDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Message;
import com.openclassrooms.P3_API_Portail_locataire.models.Rental;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import com.openclassrooms.P3_API_Portail_locataire.repositories.MessageRepository;
import com.openclassrooms.P3_API_Portail_locataire.services.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final RentalService rentalService;

    @Override
    public String saveMessage(CreateMessageDTO createMessageDto) {
        // Create a new Message entity.
        Message message = new Message();

        // Retrieve the User associated with the message using the userId from the DTO.
        User user = userService.getUser(createMessageDto.userId());
        message.setUser(user); // Set the user for the message.

        // Retrieve the Rental associated with the message using the rentalId from the DTO.
        Rental rental = rentalService.getRentalById(createMessageDto.rentalId());
        message.setRental(rental); // Set the rental for the message.

        // Set the message content from the DTO.
        message.setMessage(createMessageDto.message());

        // Save the message to the database and return its string representation.
        message = messageRepository.save(message);
        return message.toString();
    }
}
