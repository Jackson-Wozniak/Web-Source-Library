package com.example.library.source.controller;

import com.example.library.source.request.BookSourceRequest;
import com.example.library.source.service.BookSourceService;
import com.example.library.source.entity.BookSource;
import com.example.library.user.user.User;
import com.example.library.user.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/sources/book")
@AllArgsConstructor
public class BookSourceController {

    @Autowired
    private final BookSourceService bookSourceService;
    @Autowired
    private final RegistrationService registrationService;

    @RequestMapping
    public ResponseEntity<?> getAllWebSourcesByUser(@RequestParam("token") String token){
        try{
            User user = registrationService.confirmToken(token);
            return ResponseEntity.ok(user.getBookSources());
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveWebSource(@RequestParam("token") String token, @RequestBody BookSourceRequest sourceRequest){
        try{
            User user = registrationService.confirmToken(token);
            bookSourceService.saveBookSource(new BookSource(user, sourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("New Source Saved");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateWebSource(@RequestParam("token") String token, @RequestBody BookSourceRequest sourceRequest){
        User user = registrationService.confirmToken(token);
        try{
            bookSourceService.updateBookSource(new BookSource(user, sourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Source Updated");
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteWebSource(@RequestParam("token") String token, @RequestBody BookSourceRequest sourceRequest){
        User user = registrationService.confirmToken(token);
        try{
            bookSourceService.deleteBookSource(new BookSource(user, sourceRequest));
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Source Deleted");
    }
}
