package com.springintro.usersystem.model.dtos;

import javax.validation.constraints.NotNull;

import static com.springintro.usersystem.constants.GlobalMessages.PASSWORD_NOT_NULL;
import static com.springintro.usersystem.constants.GlobalMessages.USERNAME_NOT_NULL;

public class UserLoginDto {

    private String username;
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @NotNull(message = USERNAME_NOT_NULL)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = PASSWORD_NOT_NULL)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
