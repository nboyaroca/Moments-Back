package com.factoria.moments.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeRequestDto {
    private Long momentId;
    private Long userId;
}
