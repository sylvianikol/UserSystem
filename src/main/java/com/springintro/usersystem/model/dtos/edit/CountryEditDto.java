package com.springintro.usersystem.model.dtos.edit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.springintro.usersystem.constants.GlobalConstants.COUNTRY_REGEX;
import static com.springintro.usersystem.constants.GlobalMessages.*;

public class CountryEditDto {

    private String name;

    public CountryEditDto() {
    }

    public CountryEditDto(String name) {
        this.name = name;
    }

    @NotEmpty(message = COUNTRY_NOT_EMPTY)
    @NotNull(message = COUNTRY_NOT_NULL)
    @Pattern(regexp = COUNTRY_REGEX, message = COUNTRY_INVALID)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
