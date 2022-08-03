package com.factoria.moments.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MomentRequestDto {
    private String title;
    private String description;
    private String imgUrl;
    /*private Long userId; No cal perquè tenim usuari autenticat*/
}
