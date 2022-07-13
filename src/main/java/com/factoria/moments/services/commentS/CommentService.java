package com.factoria.moments.services.commentS;

import com.factoria.moments.models.Comment;
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
    public Comment getById(Long id) {
        return commentRepository.findById(id).get();
    }


    @Override
    public Comment save(Comment newComment) {
        return commentRepository.save(newComment);
    }


// l'sprint no demana crear comentaris
    /* Comment createComment(@RequestBody CommentRequestDto commentDto) {
        var newComment = new Comment();
        newComment.setComment(commentDto.getComment());
        var moment = this.momentRepository.findById(commentDto.getMomentId());
        newComment.setMoment(moment);
        return this.commentRepository.save(newComment);
    }*/
}
