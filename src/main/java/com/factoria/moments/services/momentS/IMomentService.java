package com.factoria.moments.services.momentS;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;

import java.util.List;

public interface IMomentService {

    //    List<Moment> findAll();
// ho podem mutejar perquè ja està implementat pel JPA

    Moment getWholeMoment(Long id);

/*    List<MomentResponseDto> getAll(User authUser);*/

    List<MomentResponseDto> getAll();

    MomentResponseDto findById(Long id);

    MomentResponseDto createMoment(MomentRequestDto momentDto, User authUser);

    MomentResponseDto updateMoment(Long id, MomentRequestDto updatedMoment, User authUser);

    boolean deleteById(Long id, User authUser);

    List<MomentResponseDto> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String search, User authUser);

    boolean deleteByUser(Long delId, User auth);
}
