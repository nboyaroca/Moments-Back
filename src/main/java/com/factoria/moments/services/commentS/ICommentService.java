package com.factoria.moments.services.commentS;

import com.factoria.moments.models.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> findAll();

    Comment save(Comment newComment);

  // l'sprint NO demana crear un comentari
  /* Comment createComment(CommentRequestDto commentDto, Moment momentDto);*/

}
