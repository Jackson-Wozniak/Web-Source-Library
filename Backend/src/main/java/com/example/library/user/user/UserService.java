package com.example.library.user.user;

import com.example.library.user.token.ConfirmationToken;
import com.example.library.user.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findById(username).orElseThrow(() -> new RuntimeException("No User"));
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findById(user.getUsername())
                .isPresent();
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);

        ConfirmationToken confirmationToken = confirmationTokenService.createConfirmationToken(user);

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return confirmationToken.getToken();
    }

    public int enableUser(String username) {
        return userRepository.enableUser(username);
    }
}
