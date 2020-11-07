package com.springintro.usersystem.model.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownSeedDto {

    @Expose
    private String name;

    public TownSeedDto() {
    }

    @NotNull(message = "Town can not be null!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
