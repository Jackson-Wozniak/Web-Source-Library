package com.example.library;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SourceService {

    @Autowired
    private final SourceRepository sourceRepository;
    @Autowired
    private final TagService tagService;

    public void saveSource(Source source){
        tagService.addNewTags(source);
        sourceRepository.save(source);
    }

    public List<Source> findAllSources(){
        return sourceRepository.findAll();
    }

    public List<Source> findAllSourcesByTag(String tag){
        return sourceRepository.findAll().stream()
                .filter(source -> source.getTags().contains(tag))
                .collect(Collectors.toList());
    }
}
