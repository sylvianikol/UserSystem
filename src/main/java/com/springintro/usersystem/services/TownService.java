package com.springintro.usersystem.services;

import com.springintro.usersystem.model.dtos.TownIdDto;
import com.springintro.usersystem.model.dtos.edit.TownEditDto;
import com.springintro.usersystem.model.entities.Town;

public interface TownService {

    boolean existsTown(String townName);

    Town findByName(String townName);

    TownIdDto saveTown(TownEditDto townEditDto);

    Town findById(Long id);
}
