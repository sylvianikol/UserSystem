package com.springintro.usersystem.model.dtos.edit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.springintro.usersystem.constants.GlobalConstants.NAME_REGEX;
import static com.springintro.usersystem.constants.GlobalMessages.*;

public class UserEditNameDto {

    private Long id;
    private String firstName;
    private String lastName;

    public UserEditNameDto() {
    }

    public UserEditNameDto(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = FIRST_NAME_EMPTY)
    @Pattern(regexp = NAME_REGEX, message = FIRST_NAME_INVALID)
    @Size(min = 2, max = 30, message = FIRST_NAME_INVALID_SIZE)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = LAST_NAME_EMPTY)
    @Pattern(regexp = NAME_REGEX, message = LAST_NAME_INVALID)
    @Size(min = 2, max = 40, message = LAST_NAME_INVALID_SIZE)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
