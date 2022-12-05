package com.example.library.source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SourceDto {

    private String link;
    private String title;
    private String description;
    private List<String> tags;

    public SourceDto(Source source){
        this.link = source.getSourceId().getLink();
        this.title = source.getTitle();
        this.description = source.getDescription();
        this.tags = source.getTags();
    }
}
