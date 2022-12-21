package com.example.library.source.entity;

import com.example.library.source.request.SourceRequest;
import com.example.library.user.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
potential source types:
    Web Source
    Book Source
    Video Source
 */

@Entity(name = "source")
@Table(name = "source")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Source implements Serializable {

    @EmbeddedId
    private SourceId sourceId;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    @JsonIgnore
    private User user;

    @ElementCollection
    @CollectionTable(name = "all_tags", joinColumns = {@JoinColumn(name = "title"), @JoinColumn(name = "username")})
    private List<String> tags;

    public Source(User user, SourceRequest sourceRequest){
        this.user = user;
        this.sourceId = new SourceId(user.getUsername(), sourceRequest.getTitle());
        this.description = sourceRequest.getDescription();
        this.tags = sourceRequest.getTags();
    }

    public void addTag(String tag){
        if(!this.tags.contains(tag)){
            this.tags.add(tag);
        }
    }

    public void removeTag(String tag){
        this.tags.remove(tag);
    }
}