package com.example.library;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/source")
@AllArgsConstructor
public class SourceController {

    @Autowired
    private final SourceService sourceService;

    @RequestMapping
    public List<Source> findAllSources(){
        return sourceService.findAllSources();
    }
}
