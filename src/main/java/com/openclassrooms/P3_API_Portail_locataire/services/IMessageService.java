package com.openclassrooms.P3_API_Portail_locataire.services;

import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateMessageDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Message;

public interface IMessageService {

    /**
     * Saves a new message to the database.
     *
     * @param messageDto The DTO containing message creation details.
     * @return A string representation of the saved message.
     */
    String saveMessage(CreateMessageDTO messageDto);
}
