package com.example.library.source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SourceRequest {

    private String title;
    private String description;
    private List<String> tags;
}
