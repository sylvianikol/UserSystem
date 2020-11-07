package com.springintro.usersystem.repositories;

import com.springintro.usersystem.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndEmail(String username, String email);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
