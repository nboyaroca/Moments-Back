package com.factoria.moments.services.likeS;

import com.factoria.moments.models.Like;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.ILikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService implements ILikeService{

    private ILikeRepository likeRepository;

    public LikeService(ILikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    @Override
    public Like getById(Long id) {
        return likeRepository.findById(id).get();
    }




    @Override
    public Like createLike(Like like, User authUser) {
        return null;
    }

}
