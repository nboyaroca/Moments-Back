package com.factoria.moments.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequestDto {
    private Long momentId;
    /*private Long userId; No cal perquè agafarà l'autenticat facade*/
}
