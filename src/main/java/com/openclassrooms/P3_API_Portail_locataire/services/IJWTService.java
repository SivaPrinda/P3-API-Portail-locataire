package com.openclassrooms.P3_API_Portail_locataire.services;

import org.springframework.security.core.Authentication;

public interface IJWTService {
    String generateToken(Authentication authentication);
}