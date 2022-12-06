package com.example.library.source;

import com.example.library.user.user.UserService;
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
    private final UserService userService;

    public void saveSource(Source source){
        //Add tags from new source to user tags if they don't already exist
        //User list of tags allows for filtering of sources on UI
        userService.addTagsToUser(source.getSourceId().getUser(), source.getTags());
        if(!sourceValidated(source)){
            throw new SourceException("Source cannot be created: duplicate source");
        }
        sourceRepository.save(source);
    }

    public void updateSource(Source source){
        //Add tags from new source to user tags if they don't already exist
        //User list of tags allows for filtering of sources on UI
        userService.addTagsToUser(source.getSourceId().getUser(), source.getTags());
        if(sourceRepository.findById(source.getSourceId()).isEmpty()){
            throw new SourceException("Source cannot be updated: cannot find source");
        }
        sourceRepository.save(source);
    }

    public void deleteSource(Source source){
        if(sourceRepository.findById(source.getSourceId()).isEmpty()){
            throw new SourceException("Source cannot be updated: cannot find source");
        }
        sourceRepository.delete(source);
    }

    public List<Source> getAllSourcesByUser(String username){
        return sourceRepository.findSourceByName(username);
    }

    public List<Source> getAllSourcesByTag(String tag, String username){
        return sourceRepository.findSourceByName(username).stream()
                .filter(source -> source.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    public List<Source> getAllSourcesByTag(List<String> tags, String username){
        List<Source> sources = sourceRepository.findAll();
        return null;
    }

    //returns true if source does not already exist, false if it does
    public boolean sourceValidated(Source source){
        return sourceRepository.findById(source.getSourceId()).isEmpty();
    }
}
