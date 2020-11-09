package com.springintro.usersystem.model.dtos.edit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.springintro.usersystem.constants.GlobalConstants.COUNTRY_REGEX;
import static com.springintro.usersystem.constants.GlobalConstants.NAME_REGEX;
import static com.springintro.usersystem.constants.GlobalMessages.*;

public class UserEditCountryDto {

    private Long id;
    private String countryName;

    public UserEditCountryDto() {
    }

    public UserEditCountryDto(Long id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Pattern(regexp = COUNTRY_REGEX, message = COUNTRY_INVALID)
    @NotEmpty(message = COUNTRY_NOT_EMPTY)
    @NotNull(message = COUNTRY_NOT_NULL)
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
