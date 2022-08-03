package com.factoria.moments.faker;

import com.factoria.moments.models.Moment;
import com.factoria.moments.repositories.IMomentRepository;
import com.factoria.moments.repositories.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SeedDataService { //quan s'engega l'aplicació s'injecta el seed

    private IMomentRepository momentRepository;
    private IUserRepository userRepository;
    private PasswordEncoder encoder;

    public SeedDataService(IMomentRepository momentRepository, IUserRepository userRepository, PasswordEncoder encoder) {
        this.momentRepository = momentRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostConstruct // després de construir aquest bean (o component) Seed, s'executarà la funció createDate, just quan s'executi l'app
    public void createData() {
        if (!momentRepository.findAll().isEmpty()) return;

        var user = userRepository.findById(1L).get();
        var moment = new Moment();
        moment.setPublisher(user);
        moment.setTitle("testMoment");
        moment.setDescription("testDescription");
        moment.setImgUrl("img.jpg");
        momentRepository.save(moment);
    }
}
