package com.factoria.moments.services.momentS;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.exceptions.BadRequestException;
import com.factoria.moments.exceptions.NotFoundException;
import com.factoria.moments.mappers.MomentMapper;
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
    public List<MomentResponseDto> getAll(User authUser) {
       return new MomentMapper().mapMultipleMomentsToListResponse(momentRepository.findAll(), authUser);
    } // la funció getAll() ens torna el findAll() del repository


    // Servei findById SENSE control d'errors
    /*@Override
    public Moment findById(Long id) {
        var moment = this.momentRepository.findById(id).get();
        return moment;
    }*/

    // Servei findById AMB control d'errors
    @Override
    public Moment findById(Long id) {
        var opMoment = momentRepository.findById(id);
        if (opMoment.isEmpty()) throw new NotFoundException("Moment Not Found", "M-150");
        System.out.println(opMoment.get().likesCount());
        return opMoment.get();
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
    public Moment updateMoment(Long id, MomentRequestDto updatedMoment, User authUser) {
        var moment = momentRepository.findById(id);
        if (moment.isEmpty()) throw new NotFoundException("Moment doesn't exist", "M-404");
        if (moment.get().getPublisher()!=authUser) throw new BadRequestException("Only the publisher can update his moment", "M-007");
        moment.get().setTitle(updatedMoment.getTitle());
        moment.get().setDescription(updatedMoment.getDescription());
        moment.get().setImgUrl(updatedMoment.getImgUrl());
        moment.get().setPublisher(authUser);
        return momentRepository.save(moment.get());
    }

    @Override
    public boolean deleteById(Long id, User authUser) {
        System.out.println(authUser);
        var moment= momentRepository.findById(id);
        if (moment.get().getPublisher()!=authUser) throw new BadRequestException("Only the publisher can delete his moment", "M-008");
        this.momentRepository.delete(moment.get());
        return true;
    }

    @Override
    public List<Moment> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String search) {
        return momentRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(search);
    }

    // Com puc saber si aquest mètode està bé?
    public boolean deleteByUser(Long delId, User auth) {
        Moment moment = momentRepository.findById(delId).get();
        if(moment.getPublisher().getId() != auth.getId()) return false;
        momentRepository.delete(moment);
        return true;
    }
}
