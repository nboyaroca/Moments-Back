package com.factoria.moments.repositories;

import com.factoria.moments.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//capa que gestiona la base de dades, busca, guarda, borra i modifica

public interface IMomentRepository extends JpaRepository <Moment, Long> {

//    List<Moment> findAll();
// ho podem mutejar perquè ja està implementat pel JPA

    Optional<Moment> findById(Long id);

    @Override
    void deleteById(Long id);

}
