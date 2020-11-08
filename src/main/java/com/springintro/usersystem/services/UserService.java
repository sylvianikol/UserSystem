package com.springintro.usersystem.services;

import com.springintro.usersystem.model.dtos.*;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    UserEditDto loginUser(UserLoginDto userLoginDto);

    void setNewAge(UserEditAgeDto userEditAgeDto);

    void setNewEmail(UserEditEmailDto userEditEmailDto);
}
