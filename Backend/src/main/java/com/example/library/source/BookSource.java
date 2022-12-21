package com.example.library.source;

import com.example.library.user.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "book_source")
@Table(name = "book_source")
@Getter
@Setter
@NoArgsConstructor
public class BookSource extends Source{

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "author")
    private String author;

    public BookSource(User user, BookSourceRequest bookSourceRequest) {
        super(user, bookSourceRequest);
        this.bookTitle = bookSourceRequest.getBookTitle();
        this.author = bookSourceRequest.getAuthor();
    }
}
