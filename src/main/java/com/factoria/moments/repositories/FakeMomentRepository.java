package com.factoria.moments.repositories;


import com.factoria.moments.models.Moment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FakeMomentRepository {

    private List<Moment> getMoments() {
        return List.of(
                new Moment("london", "the uk capital", "url", 1L),
                new Moment("rome", "italy capital", "url", 2L),
                new Moment("new york", "important city", "url", 3L));
    }

    public List<Moment> findAll() {
        return getMoments();
    }


    public Moment findById(Long id) {
        var momentsList = this.getMoments();
        var moment = momentsList.stream()
                .filter(item -> item.getId() == id)
                .findFirst().get();
        return moment;
    }


}
