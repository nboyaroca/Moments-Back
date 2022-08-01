package com.factoria.moments.controllers;

import com.factoria.moments.dtos.LikeRequestDto;
import com.factoria.moments.models.Like;
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
    private IUserService userService;

    public LikeController(ILikeService likeService, IMomentService momentService, IUserService userService) {
        this.likeService = likeService;
        this.userService = userService;
    }

    // GET all likes
    @GetMapping("/likes")
    ResponseEntity<List<Like>> getAll() {
        var likes = likeService.getAll();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // GET like by id
    @GetMapping("/likes/{id}")
    ResponseEntity<Like> getById(@PathVariable Long id) {
        Like like = likeService.getById(id);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    // GET like by moment id
    @GetMapping("/moments/{id}/likes")
    ResponseEntity<List<Like>> getMomentLikes(@PathVariable Long id) {
        var likes = likeService.getAllByMomentId(id);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // POST add a like
    @PostMapping("/likes")
    ResponseEntity<Boolean> createLike(@RequestBody LikeRequestDto likeRequest) {
        User authUser = getAuthUser(likeRequest.getUserId());
        var like = likeService.toggleLike(likeRequest, authUser);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    private User getAuthUser(Long id) {
        return userService.getById(id);
    }

}


