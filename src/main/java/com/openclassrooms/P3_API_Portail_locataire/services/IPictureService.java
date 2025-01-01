package com.openclassrooms.P3_API_Portail_locataire.services;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {

    /**
     * Saves a picture to the file system.
     *
     * @param file The picture file uploaded by the user.
     * @return The URL to access the saved picture.
     */
    String savePicture(MultipartFile file);

    /**
     * Retrieves a picture as a byte array.
     *
     * @param id The unique identifier of the picture (file name).
     * @return The picture as a byte array.
     */
    byte[] getPicture(String id);
}
