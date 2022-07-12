package com.factoria.moments.services.userS;

import com.factoria.moments.models.User;

import java.util.List;

public interface IUserService {

    User getById(Long id);

    List<User> getAll();

}
