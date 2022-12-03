package com.example.library.user.user.registration;

import com.example.library.user.token.ConfirmationToken;
import com.example.library.user.token.ConfirmationTokenService;
import com.example.library.user.user.User;
import com.example.library.user.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerNewUser(@RequestBody LoginRequest loginRequest){
        try{
            //returns JWT token after registration
            return ResponseEntity.ok(registrationService.register(loginRequest));
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginAsUser(@RequestBody LoginRequest request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));

            ConfirmationToken confirmationToken = confirmationTokenService.createConfirmationToken(
                    (User) userService.loadUserByUsername(authentication.getName()));

            confirmationTokenService.saveConfirmationToken(confirmationToken);

            return ResponseEntity.ok().body(confirmationToken.getToken());
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/confirm")
    public ResponseEntity<?> confirmUserToken(@RequestParam("token") String token){
        try{
            registrationService.confirmToken(token);

            return ResponseEntity.ok("Token Validated");
        }catch (Exception ex){
            return new ResponseEntity<>("Invalid Configuration Token: " +
                    ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
