package com.factoria.moments.services.likeS;

import com.factoria.moments.dtos.LikeRequestDto;
import com.factoria.moments.models.Like;
import com.factoria.moments.models.User;

import java.util.List;

public interface ILikeService {
    List<Like> getAll();

    Like getById(Long id);

    List<Like> getAllByMomentId(Long id);

    Boolean toggleLike(LikeRequestDto likeRequest, User authUser);

}
