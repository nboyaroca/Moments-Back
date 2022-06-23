package com.factoria.moments.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.util.Locale;

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




    // CONSTRUCTORS

    public Moment(String title, String description, String imgUrl, Long id) {
        this.title = title.toLowerCase();
        this.description = description.toLowerCase();
        this.imgUrl = imgUrl;
        this.id = id;
    }


}
