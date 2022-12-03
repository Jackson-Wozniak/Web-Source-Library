package com.example.library.user.user.registration;

import com.example.library.user.token.ConfirmationToken;
import com.example.library.user.token.ConfirmationTokenService;
import com.example.library.user.user.User;
import com.example.library.user.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final UserService userService;
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;

    public String register(LoginRequest request) {
        return userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getPassword()
                )
        );
    }

    @Transactional
    public User confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

//        if (confirmationToken.getConfirmedAt() != null) {
//            throw new IllegalStateException("email already confirmed");
//        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getUsername());
        return confirmationToken.getUser();
    }
}
