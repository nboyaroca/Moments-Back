package com.factoria.moments.controllers;

import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.repositories.ICommentRepository;
import com.factoria.moments.repositories.IMomentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    private ICommentRepository commentRepository;
    private IMomentRepository momentRepository;

    public CommentController(ICommentRepository commentRepository, IMomentRepository momentRepository) {
        this.commentRepository = commentRepository;
        this.momentRepository = momentRepository;
    }

    @GetMapping("/comments")
    List<Comment> getAll() {
        return this.commentRepository.findAll();
    }

    @PostMapping("/comments")
    Comment createComment(@RequestBody CommentRequestDto commentDto) {
        var newComment = new Comment();
        newComment.setComment(commentDto.getComment());
        var moment = this.momentRepository.findById(commentDto.getMomentId()).get();
        newComment.setMoment(moment);
        return this.commentRepository.save(newComment);
    }
}
