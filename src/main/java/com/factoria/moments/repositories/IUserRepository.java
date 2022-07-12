package com.factoria.moments.repositories;

import com.factoria.moments.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
