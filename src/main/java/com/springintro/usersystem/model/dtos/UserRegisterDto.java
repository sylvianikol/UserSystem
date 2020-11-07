package com.springintro.usersystem.model.dtos;

import com.springintro.usersystem.validators.ExtendedEmailValidator;
import com.springintro.usersystem.validators.ValidPassword;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.springintro.usersystem.constants.GlobalMessages.*;

public class UserRegisterDto {

    private String username;
    private String password;
    private String email;
    private LocalDateTime registeredOn;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registeredOn = LocalDateTime.now();
    }

    @NotNull(message = USERNAME_NOT_NULL)
    @Length(min = 4, max = 30, message = USERNAME_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = PASSWORD_NOT_NULL)
    @ValidPassword(message = PASSWORD_NOT_VALID)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = EMAIL_NOT_NULL)
    @ExtendedEmailValidator
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

}
