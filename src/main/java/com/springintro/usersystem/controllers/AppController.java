package com.springintro.usersystem.controllers;

import com.google.gson.Gson;
import com.springintro.usersystem.io.InputReader;
import com.springintro.usersystem.io.KeyIn;
import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.dtos.CountrySeedDto;
import com.springintro.usersystem.model.dtos.UserLoginDto;
import com.springintro.usersystem.model.dtos.UserRegisterDto;
import com.springintro.usersystem.services.CountryService;
import com.springintro.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import static com.springintro.usersystem.constants.GlobalConstants.COUNTRIES_FILE_PATH;
import static com.springintro.usersystem.constants.GlobalConstants.MENU_SELECTION;
import static com.springintro.usersystem.constants.GlobalMessages.COUNTRIES_SEEDED;

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

        // Seed Countries and Towns
//        this.seedCountriesWithTowns();

        // Register User
//         this.registerNewUser();

        // Login User
         this.loginUser();

         this.editProfileInfo();

   }

    private void editProfileInfo() {

        this.writer.writeLine(String.format(MENU_SELECTION));
        int option = KeyIn.inInt(" Select option: ");

        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                System.exit(0);
            default:
                break;
        }
    }

    private void loginUser() throws IOException {
        String output = "ERROR!";

        while (output.startsWith("ERROR!")) {
            this.writer.writeLine("Enter username:");
            String username = this.reader.readLine();
            this.writer.writeLine("Enter password:");
            String password = this.reader.readLine();

            UserLoginDto userLoginDto = new UserLoginDto(username, password);

            output = this.userService.loginUser(userLoginDto);
            this.writer.writeLine(output);
        }
    }

    private void registerNewUser() throws IOException {
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
            this.writer.writeLine(output);
        }
    }

    private void seedCountriesWithTowns() throws FileNotFoundException {
        CountrySeedDto[] countrySeedDtos = this.gson
                .fromJson(new FileReader(COUNTRIES_FILE_PATH), CountrySeedDto[].class);

        this.countryService.seedCountries(countrySeedDtos);

        this.writer.writeLine(COUNTRIES_SEEDED);
    }

}
