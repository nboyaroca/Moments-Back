package com.factoria.moments.models;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

public class Moment {

    // ATRIBUTS
    private String title = "default title";
    private String description = "default description";
    private String imgUrl = "default image.jpg";
    private Long id;




    // CONSTRUCTORS

    public Moment(String title, String description, String imgUrl, Long id) {
        this.title = title.toLowerCase();
        this.description = description.toLowerCase();
        this.imgUrl = imgUrl;
        this.id = id;
    }

    // GETTERS (retornen objectes)

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Long getId() {
        return id;
    }


}
