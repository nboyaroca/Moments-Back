package com.factoria.moments.auth.facade;

import com.factoria.moments.models.User;
import com.factoria.moments.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationFacade implements IAuthenticationFacade { //és un component que és reutilitzable a qualsevol servei, s'injectarà direcgtament en l'autowire, contructor, inclús dins d'una funció, etc.
    @Autowired
    AuthRepository authRepository;

    public Optional<User> getAuthUser() {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return authRepository.findByUsername(userName); //no get() ue retorni un optional. si està buit no hi ha ningú autenticat. si l'optional és present caldrà fer servir el service per actualitzar likes etc.
    } // retornar un optional per saber si estem autoritzats o no en els gets etc.
}
