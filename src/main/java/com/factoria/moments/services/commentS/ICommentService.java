package com.factoria.moments.services.commentS;

import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.Moment;

import java.util.List;

public interface ICommentService {
    List<Comment> findAll();

    Comment save(Comment newComment);


    /* Comment createComment(CommentRequestDto commentRequest, Moment momentDto);*/


  /*  Comment createComment(CommentRequestDto commentDto, Moment momentDto);*/

}
