package com.factoria.moments.controllers;

import com.factoria.moments.auth.facade.IAuthenticationFacade;
import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.User;
import com.factoria.moments.services.commentS.ICommentService;
import com.factoria.moments.services.momentS.IMomentService;
import com.factoria.moments.services.userS.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/")

public class CommentController {

    // ATRIBUTS
    private ICommentService commentService;
    private IMomentService momentService;
    private IAuthenticationFacade authenticationFacade;


    // CONSTRUCTOR


    public CommentController(ICommentService commentService, IMomentService momentService, IAuthenticationFacade authenticationFacade) {
        this.commentService = commentService;
        this.momentService = momentService;
        this.authenticationFacade = authenticationFacade;
    }

    // GET all comments
    @GetMapping("/comments")
    List<Comment> getAll() {
        return this.commentService.findAll();
    }

    // GET comments by id
    @GetMapping("/comments/{id}")
    Comment getById(@PathVariable Long id) {
        return this.commentService.getById(id);
    }

    // GET comments by moment id
    @GetMapping("/moments/{id}/comments")
    List<Comment> getMomentComments(@PathVariable Long id) {
        return commentService.findAllByMomentId(id);
    }


    // l'sprint NO demana crear un comentari
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/comments")
    Comment createComment(@RequestBody CommentRequestDto commentDto) {
        var authUser = authenticationFacade.getAuthUser();
        return commentService.createComment(commentDto, authUser.get());
    }

}
