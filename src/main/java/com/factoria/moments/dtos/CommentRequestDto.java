package com.factoria.moments.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequestDto {
    private String comment;
    private Long momentId;
    private Long userId;
}
