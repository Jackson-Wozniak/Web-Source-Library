package com.example.library.source;

import com.example.library.user.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookSourceService {

    @Autowired
    private final BookSourceRepository bookSourceRepository;
    @Autowired
    private final UserService userService;

    public void saveBookSource(BookSource source){
        //Add tags from new source to user tags if they don't already exist
        //User list of tags allows for filtering of webSources on UI
        userService.addTagsToUser(source.getUser(), source.getTags());
        if(!bookSourceEmpty(source)){
            throw new SourceException("Source cannot be created: duplicate source");
        }
        bookSourceRepository.save(source);
    }

    public void updateBookSource(BookSource source){
        userService.addTagsToUser(source.getUser(), source.getTags());
        if(bookSourceEmpty(source)){
            throw new SourceException("Source cannot be updated: cannot find source");
        }
        bookSourceRepository.save(source);
    }

    public void deleteBookSource(BookSource source){
        if(bookSourceEmpty(source)){
            throw new SourceException("Source cannot be updated: cannot find source");
        }
        bookSourceRepository.delete(source);
    }

    //returns true if source does not already exist, false if it does
    public boolean bookSourceEmpty(BookSource source){
        return bookSourceRepository.findById(source.getSourceId()).isEmpty();
    }
}
