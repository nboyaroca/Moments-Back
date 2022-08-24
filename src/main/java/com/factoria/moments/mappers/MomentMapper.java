package com.factoria.moments.mappers;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;

import java.util.ArrayList;
import java.util.List;

public class MomentMapper {

    // Per auth user
    public MomentResponseDto mapMomentToMomentResponseDto(Moment moment, User authUser) {
        var response = new MomentResponseDto();
        response.setId(moment.getId());
        response.setTitle(moment.getTitle());
        response.setDescription(moment.getDescription());
        response.setImgUrl(moment.getImgUrl());
        response.setPublisher(moment.getPublisher());

        response.setCommentsCount(moment.commentsCount());
        response.setLikesCount(moment.likesCount());
        response.setLiked(moment.checkIfLiked(authUser));

        return response;
    }

    public List<MomentResponseDto> mapMultipleMomentsToListResponse(List<Moment> moments, User authUser) {
        List<MomentResponseDto> responseList = new ArrayList<>();
        moments.forEach(Moment ->responseList.add(this.mapMomentToMomentResponseDto(Moment, authUser)));
        return responseList;
    }

    // Per qualsevol user
    public MomentResponseDto mapMomentToMomentResponseDto(Moment moment) {
        var response = new MomentResponseDto();
        response.setId(moment.getId());
        response.setTitle(moment.getTitle());
        response.setDescription(moment.getDescription());
        response.setImgUrl(moment.getImgUrl());
        response.setPublisher(moment.getPublisher());

        response.setCommentsCount(moment.commentsCount());
        response.setLikesCount(moment.likesCount());

        return response;
    }

    public List<MomentResponseDto> mapMultipleMomentsToListResponse(List<Moment> moments) {
        List<MomentResponseDto> responseList = new ArrayList<>();
        moments.forEach(Moment ->responseList.add(this.mapMomentToMomentResponseDto(Moment)));
        return responseList;
    }



}

