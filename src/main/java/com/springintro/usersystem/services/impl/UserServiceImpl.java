package com.springintro.usersystem.services.impl;

import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.dtos.UserLoginDto;
import com.springintro.usersystem.model.dtos.UserRegisterDto;
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
    public String loginUser(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        User user = this.userRepository
                .findByUsernameAndPassword(username, password)
                .orElse(null);

        if (user == null) {
            return String.format(USER_NOT_FOUND, username);
        }

        user.setLastTimeLoggedIn(LocalDateTime.now());
        this.userRepository.saveAndFlush(user);

        return String.format(USER_LOGGED_IN, username);
    }

    private boolean existsUser(String username, String email) {
        return this.userRepository.findByUsernameAndEmail(username, email).orElse(null) != null;
    }
}
