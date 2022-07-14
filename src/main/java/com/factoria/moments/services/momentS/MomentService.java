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
    public List<Moment> getAll() {  // get és el nom que li dono jo
        return momentRepository.findAll(); //find és del repo
    } // la funció getAll() ens torna el findAll() del repository

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
    public Moment updateMoment(Long id, MomentRequestDto updatedMoment, User authUser) {
        var moment = momentRepository.findById(id).get();
        moment.setTitle(updatedMoment.getTitle());
        moment.setDescription(updatedMoment.getDescription());
        moment.setImgUrl(updatedMoment.getImgUrl());
        moment.setPublisher(authUser);
        return momentRepository.save(moment);
    }


    @Override
    public Moment findById(Long id) {
        var moment = this.momentRepository.findById(id).get();
        return moment;
    }

    @Override
    public boolean deleteById(Long id) {
        var moment= momentRepository.findById(id).get();
        this.momentRepository.delete(moment);
        return true;
    }

    @Override
    public List<Moment> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String search) {
        return momentRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(search);
    }



}
