package com.springintro.usersystem.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springintro.usersystem.io.ConsoleReader;
import com.springintro.usersystem.io.ConsoleWriter;
import com.springintro.usersystem.io.InputReader;
import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.utils.ValidationUtil;
import com.springintro.usersystem.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public InputReader reader() {
        return new ConsoleReader();
    }

    @Bean
    public OutputWriter writer() {
        return new ConsoleWriter();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }
}
