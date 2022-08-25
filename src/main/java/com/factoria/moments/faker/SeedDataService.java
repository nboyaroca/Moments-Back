package com.factoria.moments.faker;

import com.factoria.moments.models.Moment;
import com.factoria.moments.models.Role;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import com.factoria.moments.repositories.IUserRepository;
import com.factoria.moments.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SeedDataService { //quan s'engega l'aplicació s'injecta el seed

    private IMomentRepository momentRepository;
    private IUserRepository userRepository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;

    public SeedDataService(IMomentRepository momentRepository, IUserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.momentRepository = momentRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void createUser() {
        Set<Role> userRoles = Set.of(roleRepository.findByName(Role.RoleName.ROLE_USER).get());

        var jamesBond = new User();
        jamesBond.setRoles(userRoles);
        jamesBond.setEmail("jbond@gmail.com");
        jamesBond.setUsername("James Bond");
        jamesBond.setPassword(encoder.encode("12345678"));
        jamesBond.setAvatar("https://e3.365dm.com/16/07/2048x1152/evs-xtaccess-2012-10-05-cam-c-06h41m42s20-1_3646884.jpg");

        var traveller = new User();
        traveller.setRoles(userRoles);
        traveller.setEmail("traveller@gmail.com");
        traveller.setUsername("Traveller");
        traveller.setPassword(encoder.encode("12345678"));
        traveller.setAvatar("https://www.blog.motifphotos.com/wp-content/uploads/2018/12/iStock-902506410_1200x800px-1200x800.jpg");

        userRepository.saveAll(List.of(jamesBond, traveller));

    }

    @PostConstruct // després de construir aquest bean (o component) Seed, s'executarà la funció createDate, just quan s'executi l'app
    public void createData() {
        if (!momentRepository.findAll().isEmpty()) return;

        var user = userRepository.findById(1L).get();
        var moment = new Moment();
        moment.setPublisher(user);
        moment.setTitle("City Moments");
        moment.setDescription("Share your favourites city moments");
        moment.setImgUrl("https://www.researchgate.net/publication/273900078/figure/fig13/AS:294653078327321@1447262176739/Vertical-view-of-the-circle-in-1958-with-a-west-side-very-different-from-the-1930s-Mxim.png");
        momentRepository.save(moment);
    }
}
