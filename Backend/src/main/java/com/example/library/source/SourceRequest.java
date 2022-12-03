package com.example.library.source;

import com.example.library.user.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SourceRequest {

    private String title;
    private String link;
    private String description;
    private List<String> tags;
}
