package com.springintro.usersystem.services;

import com.springintro.usersystem.model.dtos.UserLoginDto;
import com.springintro.usersystem.model.dtos.UserRegisterDto;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);
}
