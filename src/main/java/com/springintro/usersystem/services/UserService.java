package com.springintro.usersystem.services;

import com.springintro.usersystem.model.dtos.*;
import com.springintro.usersystem.model.dtos.edit.*;

import java.io.IOException;

public interface UserService {

    UserDto registerUser(UserRegisterDto userRegisterDto);

    UserDto loginUser(UserLoginDto userLoginDto);

    void setNewAge(UserEditAgeDto userEditAgeDto);

    void setNewEmail(UserEditEmailDto userEditEmailDto);

    void setNewName(UserEditNameDto userEditNameDto);

    void setNewUsername(UserEditUsernameDto userEditUsernameDto);

    boolean isValidPassword(UserDto userDto, String password);

    void setNewPassword(UserEditPassword userEditPassword);

    void setNewAddress(UserEditAddressDto userEditAddressDto) throws IOException;
}
