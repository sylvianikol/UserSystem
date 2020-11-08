package com.springintro.usersystem.model.dtos.edit;

import com.springintro.usersystem.validators.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.springintro.usersystem.constants.GlobalMessages.*;

public class UserEditPassword {

    private Long id;
    private String password;

    public UserEditPassword() {
    }

    public UserEditPassword(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = PASSWORD_NOT_EMPTY)
    @NotNull(message = PASSWORD_NOT_NULL)
    @ValidPassword(message = PASSWORD_NOT_VALID)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
