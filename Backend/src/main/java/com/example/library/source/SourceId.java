package com.example.library.source;

import com.example.library.user.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SourceId implements Serializable {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "title", nullable = false)
    private String title;
}
