package com.factoria.moments.services.commentS;

import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.User;

import java.util.List;

public interface ICommentService {
    List<Comment> findAll();


    Comment getById(Long id);

    Comment save(Comment newComment);


    List<Comment> findAllByMomentId(Long id);

    // l'sprint NO demana crear un comentari però el fem per poder usar el postman

    Comment createComment(CommentRequestDto commentDto, User authUser);
}
