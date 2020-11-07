package com.springintro.usersystem.model.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CountrySeedDto {

    @Expose
    private String name;
    @Expose
    private List<TownSeedDto> towns;

    public CountrySeedDto() {
    }

    @NotNull(message = "Country can not be null!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TownSeedDto> getTowns() {
        return towns;
    }

    public void setTowns(List<TownSeedDto> towns) {
        this.towns = towns;
    }
}
