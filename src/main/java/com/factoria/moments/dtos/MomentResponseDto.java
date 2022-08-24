package com.factoria.moments.dtos;

import com.factoria.moments.models.Comment;
import com.factoria.moments.models.Like;
import com.factoria.moments.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MomentResponseDto {
    // ATRIBUTS
    private Long id;
    private String title;
    private String description;
    private String imgUrl;
    private User publisher;

    public int commentsCount;
    public int likesCount;
    private boolean isLiked = false;

}
