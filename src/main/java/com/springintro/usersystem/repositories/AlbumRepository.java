package com.springintro.usersystem.repositories;

import com.springintro.usersystem.model.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByName(String name);
}
