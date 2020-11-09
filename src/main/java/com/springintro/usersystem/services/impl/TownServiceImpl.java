package com.springintro.usersystem.services.impl;

import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.dtos.TownIdDto;
import com.springintro.usersystem.model.dtos.edit.TownEditDto;
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
    public boolean existsTown(String townName) {
        return this.findByName(townName) != null;
    }

    @Override
    public Town findByName(String townName) {
        return this.townRepository.findByName(townName).orElse(null);
    }

    @Override
    public TownIdDto saveTown(TownEditDto townEditDto) {
        TownIdDto townIdDto = new TownIdDto();

        if (this.validationUtil.isValid(townEditDto)) {
            String townName = townEditDto.getName();
            Town town;
            if (!existsTown(townName)) {
                town = this.modelMapper.map(townEditDto, Town.class);
                this.townRepository.saveAndFlush(town);
            } else {
                town = this.findByName(townName);
            }
            townIdDto.setId(town.getId());
        } else {
            this.writer.writeLine(this.validationUtil.getViolations(townEditDto));
            townIdDto = null;
        }

        return townIdDto;
    }

    @Override
    public Town findById(Long id) {

        return this.townRepository.findById(id).orElse(null);
    }
}
