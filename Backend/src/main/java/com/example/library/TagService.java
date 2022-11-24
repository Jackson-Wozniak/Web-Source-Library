package com.example.library;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagService {

    @Autowired
    private final TagRepository tagRepository;

    public List<Tag> findAllTags(){
        return tagRepository.findAll();
    }

    public void saveTag(Tag tag){
        tagRepository.save(tag);
    }

    public void addNewTags(Source source){
        List<Tag> existingTags = tagRepository.findAll();
        source.getTags().forEach(tag -> {
            if(!existingTags.contains(new Tag(tag))){
                tagRepository.save(new Tag(tag));
            }
        });
    }
}
