package com.factoria.moments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "comments")

public class Comment {

   // ATRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String comment;

    // RELACIONS
    @ManyToOne
    @JoinColumn(name = "moment_id")
    @JsonIgnore
    private Moment moment;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private User publisher;
}
