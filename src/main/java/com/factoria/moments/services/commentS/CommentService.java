package com.factoria.moments.services.commentS;

import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.Moment;
import com.factoria.moments.repositories.ICommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService{

    private ICommentRepository commentRepository;

    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment newComment) {
        return commentRepository.save(newComment);
    }

/*    @Override
    public Comment createComment(CommentRequestDto commentRequest, Moment momentDto) {
        return null;
    }


    @Override
    public Comment createComment(CommentRequestDto commentDto) {
        var comment = new Comment();
        comment.setComment(commentDto.getComment());
        return commentRepository.save(comment);
    }*/


    /*public Comment createComment(CommentRequestDto commentDto, Moment momentDto) {
        var comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setMoment(momentDto);
        return commentRepository.save(comment);
    }*/

    /* Comment createComment(@RequestBody CommentRequestDto commentDto) {
        var newComment = new Comment();
        newComment.setComment(commentDto.getComment());
        var moment = this.momentRepository.findById(commentDto.getMomentId());
        newComment.setMoment(moment);
        return this.commentRepository.save(newComment);
    }*/
}
