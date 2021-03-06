package com.factoria.moments.controllers;

import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.User;
import com.factoria.moments.services.commentS.ICommentService;
import com.factoria.moments.services.momentS.IMomentService;
import com.factoria.moments.services.userS.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/")

public class CommentController {

    // ATRIBUTS
    private ICommentService commentService;
    private IMomentService momentService;
    private IUserService userService;


    // CONSTRUCTOR
    public CommentController(ICommentService commentService, IMomentService momentService, IUserService userService) {
        this.commentService = commentService;
        this.momentService = momentService;
        this.userService = userService;
    }


    @GetMapping("/comments")
    List<Comment> getAll() {
        return this.commentService.findAll();
    }

    @GetMapping("/comments/{id}")
    Comment getById(@PathVariable Long id) {
        return this.commentService.getById(id);
    }

    @GetMapping("/moments/{id}/comments")
    List<Comment> getMomentComments(@PathVariable Long id) {
        return commentService.findAllByMomentId(id);
    }


    // l'sprint NO demana crear un comentari

    @PostMapping("/comments")
    Comment createComment(@RequestBody CommentRequestDto commentDto) {
        User authUser = getAuthUser(commentDto.getUserId());
        return commentService.createComment(commentDto, authUser);
    }

     private User getAuthUser(Long id) {
        return userService.getById(id);
    }

}
