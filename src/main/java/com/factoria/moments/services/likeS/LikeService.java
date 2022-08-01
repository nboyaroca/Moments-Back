package com.factoria.moments.services.likeS;

import com.factoria.moments.dtos.LikeRequestDto;
import com.factoria.moments.exceptions.BadRequestException;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.Like;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.ILikeRepository;
import com.factoria.moments.services.momentS.IMomentService;
import com.factoria.moments.services.userS.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService implements ILikeService{

    private ILikeRepository likeRepository;
    private IUserService userService;
    private IMomentService momentService;

    // CONSTRUCTOR
    public LikeService(ILikeRepository likeRepository, IUserService userService, IMomentService momentService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.momentService = momentService;
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
    public List<Like> getAllByMomentId(Long id) {
        return likeRepository.getByMomentId(id);
    }

    @Override
    public Like createLike(LikeRequestDto likeRequest, User authUser) {
        // busca el moment i si no existeix, com que tenim excepció, ens dirà que no hi ha moment
        var moment = momentService.findById(likeRequest.getMomentId());
        // si el publicador del moment no és el mateix que el liker, llença un error
        if (moment.getPublisher()==authUser) throw new BadRequestException("Publisher can't like his own moment", "M-420");
        // defineixo el like
        var like = new Like();
        like.setMoment(moment);
        like.setLiker(authUser);

        return likeRepository.save(like);
    }
}
