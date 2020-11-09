package com.springintro.usersystem.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    private String name;

    public Country() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
