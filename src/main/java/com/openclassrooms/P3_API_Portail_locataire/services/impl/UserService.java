package com.openclassrooms.P3_API_Portail_locataire.services.impl;

import com.openclassrooms.P3_API_Portail_locataire.Exception.ResponseEntityException;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.LoginUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.request.RegisterUserDTO;
import com.openclassrooms.P3_API_Portail_locataire.dto.response.UserDTO;
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

    private UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // When user is not found, we throw an exception which will be caught by exception handler
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "User %s not found", username));
    }

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

    @Override
    public UserDTO getConnectedUser() {
        // To get connected user, we use the subject of the jwt token. It contains the mail of the user
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String subject = jwt.getSubject();
        // When user is not found, we throw an exception which will be caught by exception handler
        User user = userRepository.findByEmail(subject).orElseThrow(() -> new ResponseEntityException(HttpStatus.UNAUTHORIZED, "User not found"));
        // Map the User entity to UserDTO
        return mapToUserDTO(user);

    }

    @Override
    public UserDTO getUser(Long id) {
        // When user is not found, we throw an exception which will be caught by exception handler
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "User %d not found", id));
        // Map the User entity to UserDTO
        return mapToUserDTO(user);
    }

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