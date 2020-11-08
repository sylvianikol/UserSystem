package com.springintro.usersystem.model.dtos.edit;

import com.springintro.usersystem.validators.ExtendedEmailValidator;

import javax.validation.constraints.NotNull;

import static com.springintro.usersystem.constants.GlobalMessages.EMAIL_NOT_NULL;

public class UserEditEmailDto {

    private Long id;
    private String email;

    public UserEditEmailDto() {
    }

    public UserEditEmailDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = EMAIL_NOT_NULL)
    @ExtendedEmailValidator
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
