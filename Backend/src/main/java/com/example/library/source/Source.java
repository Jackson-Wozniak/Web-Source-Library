package com.example.library.source;

import com.example.library.user.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "source")
@Table(name = "source")
@Getter
@Setter
@NoArgsConstructor
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "all_tags", joinColumns = @JoinColumn(name = "tag"))
    @Column(name = "tags")
    private List<String> tags;

    public Source(User user, String title, String link, String description, List<String> tags) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.tags = tags;
    }

    public Source(User user, SourceRequest sourceRequest){
        this.user = user;
        this.title = sourceRequest.getTitle();
        this.link = sourceRequest.getLink();
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