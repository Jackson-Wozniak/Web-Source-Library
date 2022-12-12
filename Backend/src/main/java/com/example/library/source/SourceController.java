package com.example.library.source;

import com.example.library.user.user.User;
import com.example.library.user.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sources")
@AllArgsConstructor
public class SourceController {

    @Autowired
    private final SourceService sourceService;
    @Autowired
    private final RegistrationService registrationService;

    @RequestMapping
    public ResponseEntity<?> getAllSourcesByUser(@RequestParam("token") String token){
        try{
            User user = registrationService.confirmToken(token);
            return ResponseEntity.ok(user.getSources());
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveSource(@RequestParam("token") String token, @RequestBody WebSourceRequest webSourceRequest){
        try{
            User user = registrationService.confirmToken(token);
            sourceService.saveSource(new WebSource(user, webSourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("New Source Saved");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateSource(@RequestParam("token") String token, @RequestBody WebSourceRequest webSourceRequest){
        User user = registrationService.confirmToken(token);
        try{
            sourceService.updateSource(new WebSource(user, webSourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Source Updated");
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteSource(@RequestParam("token") String token, @RequestBody WebSourceRequest webSourceRequest){
        User user = registrationService.confirmToken(token);
        try{
            sourceService.deleteSource(new WebSource(user, webSourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Source Deleted");
    }
}
