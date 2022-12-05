package com.example.library.source;

import com.example.library.user.user.User;
import com.example.library.user.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/sources")
@AllArgsConstructor
public class SourceController {

    @Autowired
    private final SourceService sourceService;
    @Autowired
    private final RegistrationService registrationService;

    @RequestMapping
    public List<SourceDto> getAllSourcesByUser(@RequestParam("token") String token){
        User user = registrationService.confirmToken(token);
        return sourceService.getAllSourcesByUser(user.getUsername()).stream()
                .map(SourceDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/save")
    public void saveSourceToUser(@RequestParam("token") String token, @RequestBody SourceRequest sourceRequest){
        System.out.println(sourceRequest.toString());
        User user = registrationService.confirmToken(token);
        sourceService.saveSource(new Source(user, sourceRequest));
    }
}
