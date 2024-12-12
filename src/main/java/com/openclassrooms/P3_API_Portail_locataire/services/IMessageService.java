package com.openclassrooms.P3_API_Portail_locataire.services;

import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateMessageDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.Message;

public interface IMessageService {
    String saveMessage(CreateMessageDTO messageDto);
}
