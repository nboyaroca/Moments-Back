package com.factoria.moments.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
   @Column(name = "id", nullable = false)
    private Long id;

   // RELACIONS
   @ManyToOne
   @JoinColumn(name = "publisher_id")
   private User publisher;

   @OneToMany(mappedBy = "moment")
   @JsonIgnore // evita bucles a les relacions
   private List<Comment> commentsList = new ArrayList<>();


    // CONSTRUCTORS
    public Moment(String title, String description, String imgUrl, Long id) {
        this.title = title.toLowerCase();
        this.description = description.toLowerCase();
        this.imgUrl = imgUrl;
        this.id = id;
        this.publisher = new User();
    }


    // COMMENTS IN A MOMENT POT ESTAR TAMBÉ EN UN RESPONSES DTO O CRIDAR-LO PER DTO PER SERIALITZAR-LO
    public void addComment(Comment comment) {
        this.commentsList.add(comment);
    }

    @JsonSerialize
    public int commentsCount() {
        return this.commentsList.size();
    }


    // LIKE
    @OneToMany(mappedBy = "moment")
    private List<Like> likes = new ArrayList<>();

    // LIKES IN A MOMENT
    public void addLike(Like like) {
        System.out.println(like.getMoment()); // console log de java
        System.out.println(this);
        if(like.getMoment()!=this) return; // CLÀUSULA DE SALVAGUARDA
        likes.add(like);
    }

    public int likesCount() {
        return likes.size();
    }

    public boolean isLiked(User user) {
        var likeLiker = likes.stream().filter(Like -> Like.getLiker() == (user)).findFirst();
        if (likeLiker.isEmpty()) return false;
        return true;
    }
}
