package com.springintro.usersystem.services.impl;

import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.entities.Country;
import com.springintro.usersystem.model.entities.Town;
import com.springintro.usersystem.repositories.TownRepository;
import com.springintro.usersystem.services.TownService;
import com.springintro.usersystem.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final OutputWriter writer;

    @Autowired
    public TownServiceImpl(TownRepository townRepository,
                           ModelMapper modelMapper,
                           ValidationUtil validationUtil,
                           OutputWriter writer) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.writer = writer;
    }

    @Override
    public void setCountry(Country country) {
        for (Town town : country.getTowns()) {
                town.setCountry(country);
        }
        this.townRepository.saveAll(country.getTowns());
    }
}
