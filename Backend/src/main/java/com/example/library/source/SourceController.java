package com.example.library.source;

import com.example.library.user.user.User;
import com.example.library.user.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> saveSource(@RequestParam("token") String token, @RequestBody SourceRequest sourceRequest){
        User user = registrationService.confirmToken(token);
        try{
            sourceService.saveSource(new Source(user, sourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("New Source Saved");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateSource(@RequestParam("token") String token, @RequestBody SourceRequest sourceRequest){
        User user = registrationService.confirmToken(token);
        try{
            sourceService.updateSource(new Source(user, sourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Source Updated");
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteSource(@RequestParam("token") String token, @RequestBody SourceRequest sourceRequest){
        User user = registrationService.confirmToken(token);
        try{
            sourceService.deleteSource(new Source(user, sourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Source Deleted");
    }
}
