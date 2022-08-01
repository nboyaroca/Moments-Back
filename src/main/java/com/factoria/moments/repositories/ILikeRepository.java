package com.factoria.moments.repositories;

import com.factoria.moments.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {


/*
    List<Like> getByMomentId(Long id);
*/

    @Query("select l from Like l where l.moment.id = :id")
    List<Like> getByMomentId(@Param("id") Long id);

}
