package com.springintro.usersystem.services;

import com.springintro.usersystem.model.dtos.*;
import com.springintro.usersystem.model.dtos.edit.*;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    UserEditDto loginUser(UserLoginDto userLoginDto);

    void setNewAge(UserEditAgeDto userEditAgeDto);

    void setNewEmail(UserEditEmailDto userEditEmailDto);

    void setNewName(UserEditNameDto userEditNameDto);

    void setNewUsername(UserEditUsernameDto userEditUsernameDto);

    boolean isValidPassword(UserEditDto userEditDto, String password);

    void setNewPassword(UserEditPassword userEditPassword);
}
