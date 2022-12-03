package com.example.library.user.token;

import com.example.library.user.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public ConfirmationToken createConfirmationToken(User user){
        String token = UUID.randomUUID().toString();
        return new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
    }

}
