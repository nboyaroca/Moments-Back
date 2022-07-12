package com.factoria.moments.services;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import com.factoria.moments.services.momentS.MomentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MomentServiceTest {

    @Mock
    IMomentRepository momentRepository;

    @Test
    void getAllReturnsAListOfMoments() {
        var momentService = new MomentService(momentRepository);
        var momentList = List.of(new Moment(), new Moment());
        Mockito.when(momentRepository.findAll()).thenReturn(momentList);

        var sut = momentService.getAll();

        assertThat(sut.size(), equalTo(2));
    }

    @Test
    void createSavesAMomentMappedFromDTO() {
        var momentService = new MomentService(momentRepository);

        var momentRequest = new MomentRequestDto("London", "UK Capital", "london.jpg", 1L);
        var publisher = new User();
        publisher.setId(1L);

        var moment = new Moment();
        moment.setTitle("London");
        moment.setDescription("Uk Capital");
        moment.setImgUrl("london.jpg");
        moment.setId(1L);
        moment.setPublisher(publisher);

        Mockito.when(momentRepository.save(any(Moment.class))).thenReturn(moment);

        var sut = momentService.createMoment(momentRequest, publisher);

        assertThat(sut.getPublisher(), equalTo(publisher));
    }

    @Test
    void updateMoment() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase() {
    }
}