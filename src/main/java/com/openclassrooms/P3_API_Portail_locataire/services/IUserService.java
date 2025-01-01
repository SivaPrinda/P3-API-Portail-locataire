package com.openclassrooms.P3_API_Portail_locataire.services;


import com.openclassrooms.P3_API_Portail_locataire.Exception.ResponseEntityException;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.LoginUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.RegisterUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    /**
     * Registers a new user.
     *
     * @param registerUser The data transfer object containing registration details.
     * @return A JWT token for the newly registered user.
     */
    String register(RegisterUserDTO registerUser);

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The authenticated User entity.
     */
    User getConnectedUser();

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user.
     * @return The User entity.
     * @throws ResponseEntityException If the user is not found.
     */
    User getUser(Long id);

    /**
     * Authenticates a user with their email and password.
     *
     * @param login The login credentials (email and password).
     * @return A JWT token if the login is successful.
     * @throws ResponseEntityException If the credentials are invalid.
     */
    String login(LoginUserDTO login);
}
