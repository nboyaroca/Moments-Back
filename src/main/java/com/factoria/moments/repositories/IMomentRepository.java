package com.factoria.moments.repositories;

import com.factoria.moments.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMomentRepository extends JpaRepository<Moment, Long> {
    @Query("select m from Moment m " +
            "where upper(m.title) like upper(concat('%', :search, '%')) or upper(m.description) like upper(concat('%', :search, '%'))")
    List<Moment> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(@Param("search") String search);

    /*@Query("select m from Moment m " +
            "where upper(m.title) like upper(concat('%', ?1, '%')) or upper(m.description) like upper(concat('%', ?2, '%'))")
    List<Moment> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String description);*/



}
