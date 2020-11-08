package com.springintro.usersystem.controllers;

import com.google.gson.Gson;
import com.springintro.usersystem.constants.Command;
import com.springintro.usersystem.io.InputReader;
import com.springintro.usersystem.io.KeyIn;
import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.dtos.*;
import com.springintro.usersystem.model.dtos.edit.*;
import com.springintro.usersystem.services.CountryService;
import com.springintro.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.ParseException;

import static com.springintro.usersystem.constants.Command.*;
import static com.springintro.usersystem.constants.GlobalConstants.*;
import static com.springintro.usersystem.constants.GlobalMessages.*;

@Controller
public class AppController implements CommandLineRunner {

    private final CountryService countryService;
    private final UserService userService;
    private final Gson gson;
    private final InputReader reader;
    private final OutputWriter writer;

    @Autowired
    public AppController(CountryService countryService,
                         UserService userService,
                         Gson gson,
                         InputReader reader,
                         OutputWriter writer) {
        this.countryService = countryService;
        this.userService = userService;
        this.gson = gson;

        this.reader = reader;
        this.writer = writer;
    }


    @Override
    public void run(String... args) throws IOException, ParseException {

        // USERS SYSTEM Exercise

        Command command = START;

        while (command != EXIT) {
            command = this.showMainMenu();
        }

   }

    private Command showMainMenu() throws IOException {
        Command command = START;

        while (command != EXIT) {

            this.writer.writeLine(String.format(MAIN_MENU));
            int option = KeyIn.inInt(PROMPT);

            UserEditDto userEditDto = null;

            switch (option) {
                case 1:
                    userEditDto = this.loginUser();
                    this.showUserMenu(userEditDto);
                    break;
                case 2:
                    this.writer.writeLine(this.registerNewUser());
//                    command = this.showUserMenu();
                    break;
                case 3:
                    command = EXIT;
                    break;
                default:
                    this.writer.writeLine(COMMAND_NOT_VALID);
                    command = START;
                    break;
            }
        }

        return command;
    }

    private Command showUserMenu(UserEditDto userEditDto) throws IOException {
        Command command = START;

        while (command != EXIT) {
            this.writer.writeLine(String.format(USER_MENU));
            int option = KeyIn.inInt(PROMPT);

            switch (option) {
                case 1:
                    command = this.editProfile(userEditDto);
                    break;
                case 2:
                    // Add Album
                    break;
                case 3:
                    command = EXIT;
                    break;
                default:
                    this.writer.writeLine(COMMAND_NOT_VALID);
                    command = START;
                    break;
            }
        }

        return command;
    }

    private Command editProfile(UserEditDto userEditDto) throws IOException {
        Command command = START;

        while (command != BACK) {
            this.writer.writeLine(String.format(PROFILE_MENU));
            int option = KeyIn.inInt(PROMPT);

            switch (option) {
                case 1:
                    this.editAge(userEditDto);
                    break;
                case 2:
                    this.editEmail(userEditDto);
                    break;
                case 3:
                    this.editName(userEditDto);
                    break;
                case 4:
                    this.editUsername(userEditDto);
                    break;
                case 5:
                    this.editPassword(userEditDto);
                    break;
                case 6:
                    this.editAddress(userEditDto);
                    break;
                case 7:
                    command = BACK;
                    break;
                default:
                    this.writer.writeLine(COMMAND_NOT_VALID);
                    break;
            }
        }

        return command;
    }

    private void editAddress(UserEditDto userEditDto) throws IOException {
        this.writer.writeLine(ENTER_COUNTRY);
        String country = this.reader.readLine();

        if (this.countryService.existsCountry(country)) {
            // todo:
        } else {
            // todo:
        }
    }

    private void editPassword(UserEditDto userEditDto) throws IOException {
        String password;
        String check;

        do {
            this.writer.writeLine(ENTER_NEW_PASSWORD);
            password = this.reader.readLine();
            this.writer.writeLine(REPEAT_PASSWORD);
            check = this.reader.readLine();

            if (!password.equals(check)) {
                this.writer.writeLine(PASSWORD_MISMATCH);
            }
        } while (!password.equals(check));

        this.writer.writeLine(ENTER_OLD_PASSWORD);
        String oldPassword = this.reader.readLine();

        if (this.userService.isValidPassword(userEditDto, oldPassword)) {
            UserEditPassword userEditPassword =
                    new UserEditPassword(userEditDto.getId(), password);
            this.userService.setNewPassword(userEditPassword);
        } else {
            this.writer.writeLine("Old " + PASSWORD_NOT_VALID);
        }
    }

    private void editUsername(UserEditDto userEditDto) throws IOException {
        this.writer.writeLine(ENTER_NEW_USERNAME);
        String username = this.reader.readLine();

        UserEditUsernameDto userEditUsernameDto =
                new UserEditUsernameDto(userEditDto.getId(), username);

        this.userService.setNewUsername(userEditUsernameDto);
    }

    private void editName(UserEditDto userEditDto) throws IOException {
        this.writer.writeLine(ENTER_FIRST_NAME);
        String firstName = this.reader.readLine();
        this.writer.writeLine(ENTER_LAST_NAME);
        String lastName = this.reader.readLine();

        UserEditNameDto userEditNameDto =
                new UserEditNameDto(userEditDto.getId(), firstName, lastName);

        this.userService.setNewName(userEditNameDto);
    }

    private void editEmail(UserEditDto userEditDto) throws IOException {
        this.writer.writeLine(ENTER_EMAIL);
        String email = this.reader.readLine();
        UserEditEmailDto userEditEmailDto =
                new UserEditEmailDto(userEditDto.getId(), email);
        this.userService.setNewEmail(userEditEmailDto);
    }

    private void editAge(UserEditDto userEditDto) throws IOException {
        this.writer.writeLine(ENTER_AGE);
        Integer age = Integer.parseInt(this.reader.readLine());
        UserEditAgeDto userEditAgeDto = new UserEditAgeDto(userEditDto.getId(), age);
        this.userService.setNewAge(userEditAgeDto);
    }

    private UserEditDto loginUser() throws IOException {

        UserEditDto userEditDto = null;

        while (userEditDto == null) {
            this.writer.writeLine(ENTER_USERNAME);
            String username = this.reader.readLine();
            this.writer.writeLine(ENTER_PASSWORD);
            String password = this.reader.readLine();

            UserLoginDto userLoginDto = new UserLoginDto(username, password);

            userEditDto = this.userService.loginUser(userLoginDto);
        }

        return userEditDto;
    }

    private String registerNewUser() throws IOException {
        String output = "INVALID";

        while (output.startsWith("INVALID")) {
            this.writer.writeLine("Enter username:");
            String username = this.reader.readLine();
            this.writer.writeLine("Enter password:");
            String password = this.reader.readLine();
            this.writer.writeLine("Enter email:");
            String email = this.reader.readLine();

            UserRegisterDto userRegisterDto = new UserRegisterDto(username, password, email);

            output = this.userService.registerUser(userRegisterDto);

            if (output.startsWith("INVALID")) {
                this.writer.writeLine(output);
            }
        }

        return output;
    }

}
