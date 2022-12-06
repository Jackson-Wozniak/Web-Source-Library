package com.example.library.user.user;

import com.example.library.user.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final RegistrationService registrationService;

    @RequestMapping(value = "/tags")
    public List<String> getTagsByUser(@RequestParam("token") String token){
        return registrationService.confirmToken(token).getTags();
    }
}
