package com.springintro.usersystem.model.dtos.edit;

import com.springintro.usersystem.model.dtos.CountryIdDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.springintro.usersystem.constants.GlobalConstants.NAME_REGEX;
import static com.springintro.usersystem.constants.GlobalMessages.*;

public class TownEditDto {

    private String name;
    private CountryIdDto country;

    public TownEditDto() {
    }

    public TownEditDto(String name, CountryIdDto country) {
        this.name = name;
        this.country = country;
    }

    @NotEmpty(message = TOWN_NOT_EMPTY)
    @NotNull(message = TOWN_NOT_NULL)
    @Pattern(regexp = NAME_REGEX, message = TOWN_INVALID)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryIdDto getCountry() {
        return country;
    }

    public void setCountry(CountryIdDto country) {
        this.country = country;
    }
}
