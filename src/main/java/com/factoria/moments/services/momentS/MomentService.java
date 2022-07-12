package com.factoria.moments.services.momentS;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MomentService implements IMomentService{

    private IMomentRepository momentRepository;

    public MomentService(IMomentRepository momentRepository) {
        this.momentRepository = momentRepository;
    }
    @Override
    public List<Moment> getAll() {
        return momentRepository.findAll();
    }

    @Override
    public Moment createMoment(MomentRequestDto momentDto, User authUser){
        var moment = new Moment();
        moment.setTitle(momentDto.getTitle());
        moment.setDescription(momentDto.getDescription());
        moment.setImgUrl(momentDto.getImgUrl());
        moment.setPublisher(authUser);
        return momentRepository.save(moment);
    }

    @Override
    public Moment updateMoment(Long id, Moment updatedMoment, User authUser) {
        var moment = momentRepository.findById(id).get();
        moment.setTitle(updatedMoment.getTitle());
        moment.setDescription(updatedMoment.getDescription());
        moment.setImgUrl(updatedMoment.getImgUrl());
        moment.setPublisher(authUser);
        var dbMoment = momentRepository.save(moment);
        return dbMoment;
    }


    @Override
    public Moment findById(Long id) {
        var moment = this.momentRepository.findById(id).get();
        return moment;
    }

    @Override
    public void deleteById(Long id) {


    }

    @Override
    public List<Moment> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String description) {
        return momentRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(title, description);
    }



}
