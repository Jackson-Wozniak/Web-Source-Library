package com.example.library.source.controller;

import com.example.library.source.request.WebSourceRequest;
import com.example.library.source.service.WebSourceService;
import com.example.library.source.entity.WebSource;
import com.example.library.user.user.User;
import com.example.library.user.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/sources/web")
@AllArgsConstructor
public class WebSourceController {

    @Autowired
    private final WebSourceService webSourceService;
    @Autowired
    private final RegistrationService registrationService;

    @RequestMapping
    public ResponseEntity<?> getAllWebSourcesByUser(@RequestParam("token") String token){
        try{
            User user = registrationService.confirmToken(token);
            return ResponseEntity.ok(user.getWebSources());
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveWebSource(@RequestParam("token") String token, @RequestBody WebSourceRequest webSourceRequest){
        try{
            User user = registrationService.confirmToken(token);
            webSourceService.saveWebSource(new WebSource(user, webSourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("New Source Saved");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateWebSource(@RequestParam("token") String token, @RequestBody WebSourceRequest webSourceRequest){
        User user = registrationService.confirmToken(token);
        try{
            webSourceService.updateWebSource(new WebSource(user, webSourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Source Updated");
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteWebSource(@RequestParam("token") String token, @RequestBody WebSourceRequest webSourceRequest){
        User user = registrationService.confirmToken(token);
        try{
            webSourceService.deleteWebSource(new WebSource(user, webSourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Source Deleted");
    }
}
