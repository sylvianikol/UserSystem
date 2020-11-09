package com.springintro.usersystem.services;

import com.springintro.usersystem.model.dtos.CountryIdDto;
import com.springintro.usersystem.model.dtos.edit.CountryEditDto;
import com.springintro.usersystem.model.entities.Country;

import java.io.IOException;

public interface CountryService {

   boolean existsCountry(String name);

    Country findByName(String countryName);

    CountryIdDto saveCountry(CountryEditDto countryEditDto);
}
