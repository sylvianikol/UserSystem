package com.springintro.usersystem.model.dtos.edit;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.springintro.usersystem.constants.GlobalMessages.*;

public class UserEditUsernameDto {

    private Long id;
    private String username;

    public UserEditUsernameDto() {
    }

    public UserEditUsernameDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = USERNAME_NOT_EMPTY)
    @NotNull(message = USERNAME_NOT_NULL)
    @Length(min = 4, max = 30, message = USERNAME_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
