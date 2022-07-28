package com.factoria.moments.controllers;

import com.factoria.moments.models.Like;
import com.factoria.moments.services.likeS.ILikeService;
import com.factoria.moments.services.momentS.IMomentService;
import com.factoria.moments.services.userS.IUserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
