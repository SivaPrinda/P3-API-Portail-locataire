package com.openclassrooms.P3_API_Portail_locataire.services;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {
    String savePicture(MultipartFile file);
    byte[] getPicture(String id);
}
