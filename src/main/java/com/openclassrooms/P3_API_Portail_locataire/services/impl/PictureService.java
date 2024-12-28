package com.openclassrooms.P3_API_Portail_locataire.services.impl;

import com.openclassrooms.P3_API_Portail_locataire.Exception.ResponseEntityException;
import com.openclassrooms.P3_API_Portail_locataire.services.IPictureService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PictureService implements IPictureService {

    @Value("${application.pictures.path}")
    private String picturesPath;

    @Value("${application.pictures.url}")
    private String picturesUrl;

    /**
     * Saves a picture to the file system.
     *
     * @param file The picture file uploaded by the user.
     * @return The URL to access the saved picture.
     */
    @Override
    public String savePicture(MultipartFile file) {
        File pictureFolder = new File(picturesPath);

        // If picture folder does not exist, create it
        if (!pictureFolder.exists() && pictureFolder.mkdirs() || pictureFolder.exists() && pictureFolder.isDirectory()) {
            // That is the new name of the picture, we use UUID to avoid conflicts
            String pictureFileName = UUID.randomUUID().toString();
            File pictureFile = new File(pictureFolder, pictureFileName);

            try {
                // Saving the picture to the file and returning the URL
                file.transferTo(pictureFile);
                return picturesUrl + "/" + pictureFileName;
            } catch (Exception e) {
                throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save picture");
            }
        }
        throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save picture");
    }

    /**
     * Retrieves a picture as a byte array.
     *
     * @param id The unique identifier of the picture (file name).
     * @return The picture as a byte array.
     */
    @Override
    public byte[] getPicture(String id) {
        File pictureFile = new File(picturesPath, id);

        // When picture is not found, we throw an exception which will be caught by exception handler
        if (!pictureFile.exists()) {
            throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get picture");
        }

        // Reading the picture and returning it as byte array
        try (InputStream inputStream = new FileInputStream(pictureFile)) {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get picture");
        }
    }
}
