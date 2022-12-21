package com.example.library.source.service;

import com.example.library.source.entity.WebSource;
import com.example.library.source.exception.SourceException;
import com.example.library.source.repository.WebSourceRepository;
import com.example.library.user.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebSourceService {

    @Autowired
    private final WebSourceRepository webSourceRepository;
    @Autowired
    private final UserService userService;

    public void saveWebSource(WebSource webSource){
        //Add tags from new source to user tags if they don't already exist
        //User list of tags allows for filtering of webSources on UI
        userService.addTagsToUser(webSource.getUser(), webSource.getTags());
        if(!webSourceEmpty(webSource)){
            throw new SourceException("Source cannot be created: duplicate source");
        }
        webSourceRepository.save(webSource);
    }

    public void updateWebSource(WebSource webSource){
        userService.addTagsToUser(webSource.getUser(), webSource.getTags());
        if(webSourceEmpty(webSource)){
            throw new SourceException("Source cannot be updated: cannot find source");
        }
        webSourceRepository.save(webSource);
    }

    public void deleteWebSource(WebSource source){
        if(webSourceEmpty(source)){
            throw new SourceException("Source cannot be updated: cannot find source");
        }
        webSourceRepository.delete(source);
    }

    //returns true if source does not already exist, false if it does
    public boolean webSourceEmpty(WebSource source){
        return webSourceRepository.findById(source.getSourceId()).isEmpty();
    }
}
