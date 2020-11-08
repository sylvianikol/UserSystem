package com.springintro.usersystem.model.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class UserEditAgeDto {

    private Long id;
    private Integer age;

    public UserEditAgeDto() {
    }

    public UserEditAgeDto(Long id, Integer age) {
        this.id = id;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Min(value = 1, message = "Age must be min 1 and max 120 years")
    @Max(value = 120, message = "Age must be min 1 and max 120 years")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
