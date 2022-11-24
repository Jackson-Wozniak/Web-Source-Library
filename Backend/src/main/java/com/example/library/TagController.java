package com.example.library;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tag")
@AllArgsConstructor
public class TagController {

    @Autowired
    private final TagService tagService;

    @RequestMapping
    public List<Tag> findAllTags(){
        return tagService.findAllTags();
    }
}
