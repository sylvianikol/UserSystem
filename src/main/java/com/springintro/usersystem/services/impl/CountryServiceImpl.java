package com.springintro.usersystem.services.impl;

import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.dtos.CountrySeedDto;
import com.springintro.usersystem.model.entities.Country;
import com.springintro.usersystem.repositories.CountryRepository;
import com.springintro.usersystem.services.CountryService;
import com.springintro.usersystem.services.TownService;
import com.springintro.usersystem.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.springintro.usersystem.constants.GlobalMessages.COUNTRY_EXISTS;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final OutputWriter writer;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository,
                              TownService townService,
                              ModelMapper modelMapper,
                              ValidationUtil validationUtil,
                              OutputWriter writer) {
        this.countryRepository = countryRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.writer = writer;
    }

    @Override
    public void seedCountries(CountrySeedDto[] countrySeedDtos) {

        for (CountrySeedDto countrySeedDto : countrySeedDtos) {
            if (existsCountry(countrySeedDto.getName())) {
                this.writer.writeLine(
                        String.format(COUNTRY_EXISTS, countrySeedDto.getName()));
                continue;
            }

            if (this.validationUtil.isValid(countrySeedDto)) {
                Country country = this.modelMapper.map(countrySeedDto, Country.class);
                this.countryRepository.saveAndFlush(country);
                this.townService.setCountry(country);
            } else {
                this.validationUtil.getViolations(countrySeedDto);
            }

        }
    }

    private boolean existsCountry(String name) {
        return this.countryRepository.findByName(name).orElse(null) != null;
    }
}
