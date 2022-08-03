package com.factoria.moments.controllers;

import com.factoria.moments.auth.facade.IAuthenticationFacade;
import com.factoria.moments.dtos.LikeRequestDto;
import com.factoria.moments.models.Like;
import com.factoria.moments.services.likeS.ILikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class LikeController {

    private ILikeService likeService;

    public LikeController(ILikeService likeService) {
        this.likeService = likeService;
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
        var like = likeService.toggleLike(likeRequest);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

}


