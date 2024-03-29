package com.factoria.moments.services.likeS;

import com.factoria.moments.auth.facade.IAuthenticationFacade;
import com.factoria.moments.dtos.LikeRequestDto;
import com.factoria.moments.exceptions.BadRequestException;
import com.factoria.moments.models.Like;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.ILikeRepository;
import com.factoria.moments.services.momentS.IMomentService;
import com.factoria.moments.services.userS.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService implements ILikeService{

    private ILikeRepository likeRepository;
    private IAuthenticationFacade authenticationFacade;
    private IMomentService momentService;

    // CONSTRUCTOR
    public LikeService(ILikeRepository likeRepository, IAuthenticationFacade authenticationFacade, IMomentService momentService) {
        this.likeRepository = likeRepository;
        this.authenticationFacade = authenticationFacade;
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
    public Boolean toggleLike(LikeRequestDto likeRequest) {
        // busca el moment i si no existeix, com que tenim excepció, ens dirà que no hi ha moment
        var moment = momentService.getWholeMoment(likeRequest.getMomentId());
        var liker = authenticationFacade.getAuthUser();
        // si el publisher del moment no és el mateix que el liker, llença un error
        if (moment.getPublisher()==liker.get()) throw new BadRequestException("Publisher can't like his own moment", "M-420");
        // defineixo el like
        var like = new Like();
        like.setMoment(moment);
        like.setLiker(liker.get());

        var checkedLike = this.checkIfLikeAlreadyExists(like);
        if (checkedLike.isPresent()){
            return this.dislike(checkedLike.get());
        }
        return this.like(like);
    }

    private Boolean like(Like like) {
        likeRepository.save(like);
        return true;
    }

    private Boolean dislike(Like like) {
        likeRepository.delete(like);
        return false;
    }

    // Funció per saber si el like de l'usuari existeix i no repetir likes d'un mateix usuari sobre un mateix moment
    public Optional<Like> checkIfLikeAlreadyExists(Like like) {
        List<Like> likes = likeRepository.getByMomentId(like.getMoment().getId());
        return likes.stream().filter(Like -> Like.getLiker()==like.getLiker()).findFirst();
    }
}
