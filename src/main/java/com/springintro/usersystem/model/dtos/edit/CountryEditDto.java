package com.springintro.usersystem.model.dtos.edit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.springintro.usersystem.constants.GlobalMessages.COUNTRY_NOT_EMPTY;
import static com.springintro.usersystem.constants.GlobalMessages.COUNTRY_NOT_NULL;

public class CountryEditDto {

    private String name;

    public CountryEditDto() {
    }

    @NotEmpty(message = COUNTRY_NOT_EMPTY)
    @NotNull(message = COUNTRY_NOT_NULL)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
