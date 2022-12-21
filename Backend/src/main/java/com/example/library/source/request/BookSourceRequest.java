package com.example.library.source.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookSourceRequest extends SourceRequest {

    private String bookTitle;
    private String author;

    public BookSourceRequest(String title, String bookTitle, String author, String description, List<String> tags) {
        super(title, description, tags);
        this.bookTitle = bookTitle;
        this.author = author;
    }
}
