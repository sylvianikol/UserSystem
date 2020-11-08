package com.springintro.usersystem.services.impl;

import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.dtos.*;
import com.springintro.usersystem.model.entities.User;
import com.springintro.usersystem.repositories.UserRepository;
import com.springintro.usersystem.services.UserService;
import com.springintro.usersystem.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.springintro.usersystem.constants.GlobalMessages.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final OutputWriter writer;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           ValidationUtil validationUtil,
                           OutputWriter writer) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.writer = writer;
    }


    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        String email = userRegisterDto.getEmail();

        if (existsUser(username, email)) {
            return String.format(USER_EXISTS, username, email);
        }

        if (this.validationUtil.isValid(userRegisterDto)) {
            User user = this.modelMapper.map(userRegisterDto, User.class);
            user.setLastTimeLoggedIn(user.getRegisteredOn());
            this.userRepository.saveAndFlush(user);
        } else {
            return String.format(INVALID_USER_DATA,
                    this.validationUtil.getViolations(userRegisterDto));
        }

        return String.format(USER_REGISTERED, username);
    }

    @Override
    public UserEditDto loginUser(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        User user = this.userRepository
                .findByUsernameAndPassword(username, password)
                .orElse(null);

        UserEditDto userEditDto = null;

        if (user == null) {
            this.writer.writeLine(String.format(USER_NOT_FOUND, username));
            return userEditDto;
        } else {
            user.setLastTimeLoggedIn(LocalDateTime.now());
            this.userRepository.saveAndFlush(user);
        }

        this.writer.writeLine(String.format(USER_LOGGED_IN, username));
        userEditDto = this.modelMapper.map(user, UserEditDto.class);

        return userEditDto;
    }

    @Override
    public void setNewAge(UserEditAgeDto userEditAgeDto) {

        if (this.validationUtil.isValid(userEditAgeDto)) {
            User user = this.userRepository.findById(userEditAgeDto.getId()).orElse(null);
            if (user != null) {
                user.setAge(userEditAgeDto.getAge());
                this.userRepository.saveAndFlush(user);
                this.writer.writeLine(AGE_EDITED);
            } else {
                this.writer.writeLine(String.format(USER_ID_NOT_FOUND, userEditAgeDto.getId()));
            }

        } else {
            this.writer.write(this.validationUtil.getViolations(userEditAgeDto));
        }
    }

    @Override
    public void setNewEmail(UserEditEmailDto userEditEmailDto) {
        if (this.validationUtil.isValid(userEditEmailDto)) {
            User user = this.userRepository.findById(userEditEmailDto.getId()).orElse(null);
            if (user != null) {
                user.setEmail(userEditEmailDto.getEmail());
                this.userRepository.saveAndFlush(user);
                this.writer.writeLine(EMAIL_EDITED);
            } else {
                this.writer.writeLine(String.format(USER_ID_NOT_FOUND, userEditEmailDto.getId()));
            }

        } else {
            this.writer.write(this.validationUtil.getViolations(userEditEmailDto));
        }
    }

    private boolean existsUser(String username, String email) {
        return this.userRepository.findByUsernameAndEmail(username, email).orElse(null) != null;
    }
}
