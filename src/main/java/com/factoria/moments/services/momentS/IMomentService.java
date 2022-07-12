package com.factoria.moments.services.momentS;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;

import java.util.List;

public interface IMomentService {

    //    List<Moment> findAll();
// ho podem mutejar perquè ja està implementat pel JPA

    List<Moment> getAll();

    Moment createMoment(MomentRequestDto momentDto, User authUser);

    Moment updateMoment(Long id, MomentRequestDto updatedMoment, User authUser);

    Moment findById(Long id);

    void deleteById(Long id);

    List<Moment> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String search);
}
