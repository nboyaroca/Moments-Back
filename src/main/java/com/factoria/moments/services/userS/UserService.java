package com.factoria.moments.services.userS;

import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }



}
