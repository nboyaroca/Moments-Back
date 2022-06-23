package com.factoria.moments.repositories;

import com.factoria.moments.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMomentRepository extends JpaRepository <Moment, Long> {

//    List<Moment> findAll();

    Optional<Moment> findById(Long id);

    // ho podem mutejar perquè ja està implementat pel JPA
}
