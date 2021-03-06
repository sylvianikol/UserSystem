package com.springintro.usersystem.model.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.springintro.usersystem.constants.GlobalMessages.*;

public class UserLoginDto {

    private String username;
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @NotEmpty(message = USERNAME_NOT_EMPTY)
    @NotNull(message = USERNAME_NOT_NULL)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = PASSWORD_NOT_EMPTY)
    @NotNull(message = PASSWORD_NOT_NULL)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
