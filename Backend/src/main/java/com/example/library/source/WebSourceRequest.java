package com.example.library.source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class WebSourceRequest extends SourceRequest {

    private String link;

    public WebSourceRequest(String title, String link, String description, List<String> tags) {
        super(title, description, tags);
        this.link = link;
    }
}
