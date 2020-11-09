package com.springintro.usersystem.services.impl;

import com.springintro.usersystem.io.InputReader;
import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.dtos.CountryIdDto;
import com.springintro.usersystem.model.dtos.edit.CountryEditDto;
import com.springintro.usersystem.model.entities.Country;
import com.springintro.usersystem.repositories.CountryRepository;
import com.springintro.usersystem.services.CountryService;
import com.springintro.usersystem.services.TownService;
import com.springintro.usersystem.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.springintro.usersystem.constants.GlobalMessages.ENTER_COUNTRY;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final OutputWriter writer;
    private final InputReader reader;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository,
                              TownService townService,
                              ModelMapper modelMapper,
                              ValidationUtil validationUtil,
                              OutputWriter writer, InputReader reader) {
        this.countryRepository = countryRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public boolean existsCountry(String name) {
        return this.findByName(name) != null;
    }

    public CountryIdDto saveCountry(CountryEditDto countryEditDto) {
        CountryIdDto countryIdDto = new CountryIdDto();

        if (this.validationUtil.isValid(countryEditDto)) {
            String countryName = countryEditDto.getName();
            Country country;

            if (!this.existsCountry(countryName)) {
                country = this.modelMapper.map(countryEditDto, Country.class);
                this.countryRepository.saveAndFlush(country);
            } else {
                country = this.findByName(countryName);
            }
            countryIdDto.setId(country.getId());
        } else {
            this.writer.writeLine(this.validationUtil.getViolations(countryEditDto));
            countryIdDto = null;
        }

        return countryIdDto;
    }

    @Override
    public Country findByName(String countryName) {
        return this.countryRepository.findByName(countryName).orElse(null);
    }
}
