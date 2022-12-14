package com.example.library.source.entity;

import com.example.library.source.request.WebSourceRequest;
import com.example.library.user.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "web_source")
@Table(name = "web_source")
@Getter
@Setter
@NoArgsConstructor
public class WebSource extends Source {

    @Column(name = "link")
    private String link;

    public WebSource(User user, WebSourceRequest webSourceRequest) {
        super(user, webSourceRequest);
        this.link = webSourceRequest.getLink();
    }
}
