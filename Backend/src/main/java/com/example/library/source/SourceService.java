package com.example.library.source;

import com.example.library.user.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SourceService {

    @Autowired
    private final WebSourceRepository sourceRepository;
    @Autowired
    private final UserService userService;

    public void saveSource(WebSource webSource){
        //Add tags from new source to user tags if they don't already exist
        //User list of tags allows for filtering of sources on UI
        userService.addTagsToUser(webSource.getUser(), webSource.getTags());
        if(!sourceEmpty(webSource)){
            throw new SourceException("Source cannot be created: duplicate source");
        }
        sourceRepository.save(webSource);
    }

    public void updateSource(WebSource webSource){
        userService.addTagsToUser(webSource.getUser(), webSource.getTags());
        if(sourceEmpty(webSource)){
            throw new SourceException("Source cannot be updated: cannot find source");
        }
        sourceRepository.save(webSource);
    }

    public void deleteSource(WebSource source){
        if(sourceEmpty(source)){
            throw new SourceException("Source cannot be updated: cannot find source");
        }
        sourceRepository.delete(source);
    }

    //returns true if source does not already exist, false if it does
    public boolean sourceEmpty(WebSource source){
        return sourceRepository.findById(source.getSourceId()).isEmpty();
    }
}
