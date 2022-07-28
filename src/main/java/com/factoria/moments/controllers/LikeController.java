package com.factoria.moments.controllers;

import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.Like;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.services.likeS.ILikeService;
import com.factoria.moments.services.momentS.IMomentService;
import com.factoria.moments.services.userS.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class LikeController {

    private ILikeService likeService;
    private IMomentService momentService;
    private IUserService userService;

    public LikeController(ILikeService likeService, IMomentService momentService, IUserService userService) {
        this.likeService = likeService;
        this.momentService = momentService;
        this.userService = userService;
    }

    @GetMapping("/likes")
    List<Like> getAll() {
        return this.likeService.getAll();
    }

    @GetMapping("/likes/{id}")
    ResponseEntity<Like> getById(@PathVariable Long id) {
        Like like = likeService.getById(id);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }
}



    /*@PostMapping("/likes")
    Like createLike(@RequestBody Like like) {
        User authUser = getAuthUser(like.getUserId());
        return likeService.createLike(like, authUser);
    }

    private User getAuthUser(Long id) {
        return userService.getUserById(id);
    }*/


