package com.factoria.moments.controllers;

import com.factoria.moments.models.Comment;
import com.factoria.moments.services.commentS.ICommentService;
import com.factoria.moments.services.momentS.IMomentService;
import com.factoria.moments.services.userS.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

    // l'sprint NO demana crear un comentari
    /* @PostMapping("/comments")
    Comment createComment(@RequestBody CommentRequestDto commentDto) {
        return commentService.createComment(commentDto);
    }*/

    /* private User getAuthUser() {
        return userService.getById(1L);
    }*/

}
