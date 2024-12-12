package com.openclassrooms.P3_API_Portail_locataire.controllers;

import com.openclassrooms.P3_API_Portail_locataire.dto.request.CreateMessageDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.MessageDTO;
import com.openclassrooms.P3_API_Portail_locataire.services.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @PostMapping
    public ResponseEntity<MessageDTO> createMessage(@RequestBody CreateMessageDTO createMessageDTO) {
        iMessageService.saveMessage(createMessageDTO);
        return ResponseEntity.ok(new MessageDTO("Message send with success"));
    }

}
