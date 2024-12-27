package com.openclassrooms.P3_API_Portail_locataire.services.impl;

import com.openclassrooms.P3_API_Portail_locataire.Exception.ResponseEntityException;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.LoginUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.RegisterUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.models.User;
import com.openclassrooms.P3_API_Portail_locataire.repositories.UserRepository;
import com.openclassrooms.P3_API_Portail_locataire.services.IJWTService;
import com.openclassrooms.P3_API_Portail_locataire.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJWTService jwtService;

    /**
     * Loads user details by email (used as the username).
     *
     * @param username The email of the user to be loaded.
     * @return UserDetails object containing user credentials and authorities.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // When user is not found, we throw an exception which will be caught by exception handler
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "User %s not found", username));
    }

    /**
     * Registers a new user.
     *
     * @param registerUser The data transfer object containing registration details.
     * @return A JWT token for the newly registered user.
     */
    @Override
    public String register(RegisterUserDTO registerUser) {
        User user = new User();
        user.setName(registerUser.name());
        user.setEmail(registerUser.email());
        user.setPassword(passwordEncoder.encode(registerUser.password()));
        user = userRepository.save(user);
        // TWhen user is registered, the response will contain a JWT token
        return jwtService.generateToken(new UsernamePasswordAuthenticationToken(
            user, null, user.getAuthorities()
        ));
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The authenticated User entity.
     */
    @Override
    public User getConnectedUser() {
        // To get connected user, we use the subject of the jwt token. It contains the mail of the user
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String subject = jwt.getSubject();
        // When user is not found, we throw an exception which will be caught by exception handler
        User user = userRepository.findByEmail(subject).orElseThrow(() -> new ResponseEntityException(HttpStatus.UNAUTHORIZED, "User not found"));

        return user;

    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user.
     * @return The User entity.
     * @throws ResponseEntityException If the user is not found.
     */
    @Override
    public User getUser(Long id) {
        // When user is not found, we throw an exception which will be caught by exception handler
        return userRepository.findById(id).orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "User %d not found", id));

    }

    /**
     * Authenticates a user with their email and password.
     *
     * @param login The login credentials (email and password).
     * @return A JWT token if the login is successful.
     * @throws ResponseEntityException If the credentials are invalid.
     */
    @Override
    public String login(LoginUserDTO login) {
        // To make login action, first we find the user by mail and then we check if the password is correct
        // If nothing matches we throw an exception which will be caught by exception handler
        User loggedUser = userRepository.findByEmail(login.email())
            .filter(user -> passwordEncoder.matches(login.password(), user.getPassword()))
            .orElseThrow(() -> new ResponseEntityException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        // And then we generate a JWT token
        return jwtService.generateToken(new UsernamePasswordAuthenticationToken(
            loggedUser, null, loggedUser.getAuthorities()
        ));
    }
}