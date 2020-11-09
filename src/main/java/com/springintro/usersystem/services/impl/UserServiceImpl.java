package com.springintro.usersystem.services.impl;

import com.springintro.usersystem.io.InputReader;
import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.dtos.*;
import com.springintro.usersystem.model.dtos.edit.*;
import com.springintro.usersystem.model.entities.Town;
import com.springintro.usersystem.model.entities.User;
import com.springintro.usersystem.repositories.UserRepository;
import com.springintro.usersystem.services.CountryService;
import com.springintro.usersystem.services.TownService;
import com.springintro.usersystem.services.UserService;
import com.springintro.usersystem.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.springintro.usersystem.constants.GlobalMessages.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CountryService countryService;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final OutputWriter writer;
    private final InputReader reader;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           CountryService countryService,
                           TownService townService,
                           ModelMapper modelMapper,
                           ValidationUtil validationUtil,
                           OutputWriter writer,
                           InputReader reader) {
        this.userRepository = userRepository;
        this.countryService = countryService;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.writer = writer;
        this.reader = reader;
    }


    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        String email = userRegisterDto.getEmail();

        if (existsUser(username, email)) {
            this.writer.writeLine(String.format(USER_EXISTS, username, email));
            return null;
        }

        UserDto userDto;

        if (this.validationUtil.isValid(userRegisterDto)) {
            User user = this.modelMapper.map(userRegisterDto, User.class);
            user.setLastTimeLoggedIn(user.getRegisteredOn());
            this.userRepository.saveAndFlush(user);
            this.writer.writeLine(String.format(USER_REGISTERED, username));

            userDto = this.modelMapper.map(user, UserDto.class);

        } else {

            this.writer.writeLine(this.validationUtil.getViolations(userRegisterDto));
            return null;
        }

        return userDto;
    }

    @Override
    public UserDto loginUser(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        User user = this.userRepository
                .findByUsernameAndPassword(username, password)
                .orElse(null);

        UserDto userDto;

        if (user == null) {
            this.writer.writeLine(String.format(USER_NOT_FOUND, username));
            return null;
        } else {
            user.setLastTimeLoggedIn(LocalDateTime.now());
            this.userRepository.saveAndFlush(user);

            userDto = this.modelMapper.map(user, UserDto.class);
        }

        this.writer.writeLine(String.format(USER_LOGGED_IN, username));

        return userDto;
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

    @Override
    public void setNewName(UserEditNameDto userEditNameDto) {
        if (this.validationUtil.isValid(userEditNameDto)) {
            User user = this.userRepository.findById(userEditNameDto.getId()).orElse(null);
            if (user != null) {
                user.setFirstName(userEditNameDto.getFirstName());
                user.setLastName(userEditNameDto.getLastName());
                this.userRepository.saveAndFlush(user);
                this.writer.writeLine(NAMES_EDITED);
            } else {
                this.writer.writeLine(String.format(USER_ID_NOT_FOUND, userEditNameDto.getId()));
            }

        } else {
            this.writer.writeLine(INVALID_INPUT);
            this.writer.write(this.validationUtil.getViolations(userEditNameDto));
        }
    }

    @Override
    public void setNewUsername(UserEditUsernameDto userEditUsernameDto) {
        if (this.validationUtil.isValid(userEditUsernameDto)) {
            User user = this.userRepository.findById(userEditUsernameDto.getId()).orElse(null);
            if (user != null) {
                user.setUsername(userEditUsernameDto.getUsername());
                this.userRepository.saveAndFlush(user);
                this.writer.writeLine(USERNAME_EDITED);
            } else {
                this.writer.writeLine(String.format(USER_ID_NOT_FOUND, userEditUsernameDto.getId()));
            }

        } else {
            this.writer.writeLine(INVALID_INPUT);
            this.writer.write(this.validationUtil.getViolations(userEditUsernameDto));
        }
    }

    @Override
    public void setNewPassword(UserEditPassword userEditPassword) {
        if (this.validationUtil.isValid(userEditPassword)) {
            User user = this.userRepository.findById(userEditPassword.getId()).orElse(null);
            if (user != null) {
                user.setPassword(userEditPassword.getPassword());
                this.userRepository.saveAndFlush(user);
                this.writer.writeLine(PASSWORD_EDITED);
            } else {
                this.writer.writeLine(String.format(USER_ID_NOT_FOUND, userEditPassword.getId()));
            }

        } else {
            this.writer.writeLine(INVALID_INPUT);
            this.writer.write(this.validationUtil.getViolations(userEditPassword));
        }
    }

    @Override
    public void setNewAddress(UserEditAddressDto userEditAddressDto) throws IOException {

        if (this.validationUtil.isValid(userEditAddressDto)) {

           User user = this.userRepository.findById(userEditAddressDto.getId()).orElse(null);
            if (user != null) {
                Town hometown = this.townService.findById(userEditAddressDto.getHomeTown().getId());
                Town residenceTown = this.townService.findById(userEditAddressDto.getResidenceTown().getId());
                user.setHomeTown(hometown);
                user.setResidenceTown(residenceTown);
                this.userRepository.saveAndFlush(user);
                this.writer.writeLine(ADDRESS_EDITED);
            } else {
                this.writer.writeLine(String.format(USER_ID_NOT_FOUND, userEditAddressDto.getId()));
            }
        } else {
            this.writer.writeLine(this.validationUtil.getViolations(userEditAddressDto));
        }
    }

    @Override
    public boolean isValidPassword(UserDto userDto, String password) {
        return this.userRepository
                .findByIdAndPassword(userDto.getId(), password)
                .orElse(null) != null;
    }

    private boolean existsUser(String username, String email) {
        return this.userRepository.findByUsernameAndEmail(username, email).orElse(null) != null;
    }
}
