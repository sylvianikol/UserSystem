package com.springintro.usersystem.services;

import com.springintro.usersystem.model.dtos.CountrySeedDto;

public interface CountryService {

    void seedCountries(CountrySeedDto[] dtos);
}
