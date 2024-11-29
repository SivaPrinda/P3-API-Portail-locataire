package com.openclassrooms.P3_API_Portail_locataire.services;


import com.openclassrooms.P3_API_Portail_locataire.dto.request.LoginUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.RegisterUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    String register(RegisterUserDTO registerUser);

    User getConnectedUser();

    User getUser(int id);

    String login(LoginUserDTO login);
}
