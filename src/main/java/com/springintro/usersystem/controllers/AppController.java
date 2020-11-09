package com.springintro.usersystem.controllers;

import com.google.gson.Gson;
import com.springintro.usersystem.constants.Command;
import com.springintro.usersystem.io.InputReader;
import com.springintro.usersystem.io.KeyIn;
import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.TownType;
import com.springintro.usersystem.model.dtos.*;
import com.springintro.usersystem.model.dtos.edit.*;
import com.springintro.usersystem.services.AlbumService;
import com.springintro.usersystem.services.CountryService;
import com.springintro.usersystem.services.TownService;
import com.springintro.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.ParseException;

import static com.springintro.usersystem.constants.Command.*;
import static com.springintro.usersystem.constants.GlobalConstants.*;
import static com.springintro.usersystem.constants.GlobalMessages.*;
import static com.springintro.usersystem.model.TownType.HOMETOWN;
import static com.springintro.usersystem.model.TownType.RESIDENCE;

@Controller
public class AppController implements CommandLineRunner {

    private final UserService userService;
    private final CountryService countryService;
    private final TownService townService;
    private final AlbumService albumService;
    private final Gson gson;
    private final InputReader reader;
    private final OutputWriter writer;

    @Autowired
    public AppController(UserService userService,
                         CountryService countryService,
                         TownService townService,
                         AlbumService albumService,
                         Gson gson,
                         InputReader reader,
                         OutputWriter writer) {
        this.countryService = countryService;
        this.userService = userService;
        this.townService = townService;
        this.albumService = albumService;
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

        System.exit(0);

   }

    private Command showMainMenu() throws IOException {
        Command command = START;

        while (command != EXIT) {

            this.writer.writeLine(String.format(MAIN_MENU));
            int option = KeyIn.inInt(PROMPT);

            UserDto userDto = null;

            switch (option) {
                case 1:
                    userDto = this.loginUser();
                    this.showUserMenu(userDto);
                    break;
                case 2:
                    userDto = this.registerNewUser();
                    command = this.showUserMenu(userDto);
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

    private Command showUserMenu(UserDto userDto) throws IOException {
        Command command = START;

        while (command != EXIT) {
            this.writer.writeLine(String.format(USER_MENU));
            int option = KeyIn.inInt(PROMPT);

            switch (option) {
                case 1:
                    command = this.editProfile(userDto);
                    break;
                case 2:
                    command = this.createAlbum(userDto);
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

    private Command createAlbum(UserDto userDto) throws IOException {
        Command command = START;

        while (command != BACK) {
            this.writer.writeLine(ENTER_ALBUM_NAME);
            String albumName = this.reader.readLine();
            this.writer.writeLine(ENTER_BACKGROUND);
            String backgroundColor = this.reader.readLine();

            char setPublic = getSetPublic();

            AlbumCreateDto albumCreateDto =
                    new AlbumCreateDto(albumName, backgroundColor,
                            setPublic == 'y', userDto);

            command = this.albumService.createAlbum(albumCreateDto);
        }
        return command;
    }

    private char getSetPublic() throws IOException {
        char c = '_';

        while (c != 'y') {
            this.writer.writeLine(SET_TO_PUBLIC);
            c = this.reader.readLine().charAt(0);

            if (c == 'n') return c;

            if (c != 'y') {
                this.writer.writeLine(String.format(INVALID_ANSWER, c));
            }
        }

        return c;
    }

    private Command editProfile(UserDto userDto) throws IOException {
        Command command = START;

        while (command != BACK) {
            this.writer.writeLine(String.format(PROFILE_MENU));
            int option = KeyIn.inInt(PROMPT);

            switch (option) {
                case 1:
                    this.editAge(userDto);
                    break;
                case 2:
                    this.editEmail(userDto);
                    break;
                case 3:
                    this.editName(userDto);
                    break;
                case 4:
                    this.editUsername(userDto);
                    break;
                case 5:
                    this.editPassword(userDto);
                    break;
                case 6:
                    this.editAddress(userDto);
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

    private void editAddress(UserDto userDto) throws IOException {

        CountryIdDto countryIdDto = this.getCountryIdDto();

        TownIdDto homeTownIdDto = this.getTownIdDto(HOMETOWN, countryIdDto);
        TownIdDto residenceTownIdDto = this.getTownIdDto(RESIDENCE, countryIdDto);

        UserEditAddressDto userEditAddressDto =
                new UserEditAddressDto(userDto.getId(), homeTownIdDto, residenceTownIdDto);

        this.userService.setNewAddress(userEditAddressDto);
    }

    private TownIdDto getTownIdDto(TownType townType, CountryIdDto countryIdDto) throws IOException {
        TownIdDto townIdDto = null;

        while (townIdDto == null) {
            this.writer.writeLine(townType == HOMETOWN ? ENTER_HOMETOWN : ENTER_RESIDENCE);
            String townName = this.reader.readLine();

            TownEditDto townEditDto = new TownEditDto(townName, countryIdDto);
            townIdDto = this.townService.saveTown(townEditDto);
        }

        return townIdDto;
    }

    private CountryIdDto getCountryIdDto() throws IOException {
        CountryIdDto countryIdDto = null;

        while (countryIdDto == null) {
            this.writer.writeLine(ENTER_COUNTRY);
            String countryName = this.reader.readLine();

            CountryEditDto countryEditDto = new CountryEditDto(countryName);

            countryIdDto = this.countryService.saveCountry(countryEditDto);
        }

        return countryIdDto;
    }

    private void editPassword(UserDto userDto) throws IOException {
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

        if (this.userService.isValidPassword(userDto, oldPassword)) {
            UserEditPassword userEditPassword =
                    new UserEditPassword(userDto.getId(), password);
            this.userService.setNewPassword(userEditPassword);
        } else {
            this.writer.writeLine("Old " + PASSWORD_NOT_VALID);
        }
    }

    private void editUsername(UserDto userDto) throws IOException {
        this.writer.writeLine(ENTER_NEW_USERNAME);
        String username = this.reader.readLine();

        UserEditUsernameDto userEditUsernameDto =
                new UserEditUsernameDto(userDto.getId(), username);

        this.userService.setNewUsername(userEditUsernameDto);
    }

    private void editName(UserDto userDto) throws IOException {
        this.writer.writeLine(ENTER_FIRST_NAME);
        String firstName = this.reader.readLine();
        this.writer.writeLine(ENTER_LAST_NAME);
        String lastName = this.reader.readLine();

        UserEditNameDto userEditNameDto =
                new UserEditNameDto(userDto.getId(), firstName, lastName);

        this.userService.setNewName(userEditNameDto);
    }

    private void editEmail(UserDto userDto) throws IOException {
        this.writer.writeLine(ENTER_EMAIL);
        String email = this.reader.readLine();
        UserEditEmailDto userEditEmailDto =
                new UserEditEmailDto(userDto.getId(), email);
        this.userService.setNewEmail(userEditEmailDto);
    }

    private void editAge(UserDto userDto) throws IOException {
        this.writer.writeLine(ENTER_AGE);
        Integer age = Integer.parseInt(this.reader.readLine());
        UserEditAgeDto userEditAgeDto = new UserEditAgeDto(userDto.getId(), age);
        this.userService.setNewAge(userEditAgeDto);
    }

    private UserDto loginUser() throws IOException {

        UserDto userDto = null;

        while (userDto == null) {
            this.writer.writeLine(ENTER_USERNAME);
            String username = this.reader.readLine();
            this.writer.writeLine(ENTER_PASSWORD);
            String password = this.reader.readLine();

            UserLoginDto userLoginDto = new UserLoginDto(username, password);

            userDto = this.userService.loginUser(userLoginDto);
        }

        return userDto;
    }

    private UserDto registerNewUser() throws IOException {
        UserDto userDto = null;

        while (userDto == null) {
            this.writer.writeLine("Enter username:");
            String username = this.reader.readLine();
            this.writer.writeLine("Enter password:");
            String password = this.reader.readLine();
            this.writer.writeLine("Enter email:");
            String email = this.reader.readLine();

            UserRegisterDto userRegisterDto = new UserRegisterDto(username, password, email);

            userDto = this.userService.registerUser(userRegisterDto);
        }

        return userDto;
    }
}
