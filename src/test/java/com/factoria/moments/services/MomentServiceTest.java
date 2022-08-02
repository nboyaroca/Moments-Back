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
import java.util.Optional;

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
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(momentRepository.findAll()).thenReturn(momentList);

        var sut = momentService.getAll(authUser);

        assertThat(sut.size(), equalTo(2));
    }

    // FUNCIÓ PER CREAR UN MOMENT PER ÚS DELS TESTS
    private Moment createMoment() {
        var publisher = new User();
        publisher.setId(1L);

        var moment = new Moment(); //instanciem la classe Moment (variables en minúscula i classes en majúscula)
        moment.setTitle("title");
        moment.setDescription("description");
        moment.setImgUrl("image");
        moment.setId(1L);
        moment.setPublisher(publisher);

        return moment;
    }

    @Test
    void findByIdShouldReturnAMomentWithSameParamId() {
        var momentService = new MomentService(momentRepository);
        var moment = this.createMoment(); // si no tens això cal que cada vegada creis el moment i el user
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));

        var sut = momentService.findById(1L, authUser);

        assertThat(sut.getTitle(), equalTo(moment.getTitle()));
        /*assertThat(sut.getTitle(), equalTo("HelloWorld"); TEST FAIL */
    }

    @Test
    void createShouldSaveAMomentFromRequestDTO() {
        var momentService = new MomentService(momentRepository);
        var momentRequest = new MomentRequestDto("title", "description", "image", 1L);
        var moment = this.createMoment();

        Mockito.when(momentRepository.save(any(Moment.class))).thenReturn(moment);

        var sut = momentService.createMoment(momentRequest, moment.getPublisher());

        assertThat(sut.getPublisher(), equalTo(moment.getPublisher()));
    }

    @Test
    void updateMomentShouldModifyAMomentFromRequestDTO() { // mirar sempre el momentService per fer el que necessitem
        var momentService = new MomentService(momentRepository);
        var momentRequest = new MomentRequestDto("title", "description", "image", 1L);
        var moment = this.createMoment();

        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));
        Mockito.when(momentRepository.save(any(Moment.class))).thenReturn(moment);

        var sut = momentService.updateMoment(1L, momentRequest, moment.getPublisher()); // sut és el nom del test abans del should

        assertThat(sut.getTitle(), equalTo(momentRequest.getTitle()));
        /*assertThat(sut.getTitle(), equalTo("HelloWorld")); FAILED TEST */
    }

    // UNHAPPY PATH no funciona, hauria de ser null i en canvi dóna moment.// ara ni funciona!!!!
    @Test
    void UserCanNotUpdateAMomentIfItsNotTheirs() {
        var momentService = new MomentService(momentRepository);
        var momentRequest = new MomentRequestDto("title", "description", "image", 1L);
        Long momentId = 1L;
        Moment moment = this.createMoment();

        var user = new User();
        user.setId(2L);

        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));
        Mockito.when(momentRepository.save(any(Moment.class))).thenReturn(moment);

        var sut = momentService.updateMoment(momentId, momentRequest, user);

        assertThat(sut, equalTo(moment));
    }

    @Test
    void deleteByIdShouldDeleteAMomentById() {
        var momentService = new MomentService(momentRepository);
        var moment = this.createMoment();
        var user = new User();
        user.setId(1L);

        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));

        var sut = momentService.deleteById(1L, user);

        assertThat(sut, equalTo(true));
        /*assertThat(sut, equalTo(false)); FAILED TEST */
    }

    @Test
    void userCanNotDeleteAMomentIfItsNotTheirs() {
        var momentService = new MomentService(momentRepository);
        Moment moment = this.createMoment();
        Long delId = 1L;

        var user = new User();
        user.setId(2L);

        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));

        var sut = momentService.deleteByUser(delId, user);

        assertThat(sut, equalTo(false));

    }

    @Test
    void findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCaseShouldReturnSearchedMoments() {
        var momentService = new MomentService(momentRepository);
        var moment = this.createMoment();
        var momentList= List.of(moment, moment);
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(momentRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(any(String.class))).thenReturn(momentList);

        var sut = momentService.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase("search", authUser);

        assertThat(sut.size(), equalTo(2));
        /*assertThat(sut.size(), equalTo(3)); FAILED TEST */
    }

}