package com.factoria.moments.models;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "moments")
@NoArgsConstructor



public class Moment {

    // ATRIBUTS
    private String title = "default title";
    private String description = "default description";
    private String imgUrl = "default image.jpg";
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "moment")
    private List<Comment> comments = new ArrayList<>();

    @JsonSerialize
    public int commentCount() {
        return this.comments.size();
    }


    // CONSTRUCTORS
    public Moment(String title, String description, String imgUrl, Long id) {
        this.title = title.toLowerCase();
        this.description = description.toLowerCase();
        this.imgUrl = imgUrl;
        this.id = id;
    }


}
