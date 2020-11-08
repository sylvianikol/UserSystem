package com.springintro.usersystem.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String fullName;

    private String username;
    private String password;
    private String email;
    private LocalDateTime registeredOn;
    private LocalDateTime lastTimeLoggedIn;
    private Integer age;
    private boolean isDeleted;

    private Town homeTown;
    private Town residenceTown;

    private Set<User> usersWhoAreFriendsOfUser;
    private Set<User> usersWhoChoseUserAsFriend;

    private Set<Album> albums;

    public User() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Transient
    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "registered_on")
    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Column(name = "last_time_logged_in")
    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @ManyToOne
    @JoinColumn(name = "home_town")
    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    @ManyToOne
    @JoinColumn(name = "residence_town")
    public Town getResidenceTown() {
        return residenceTown;
    }

    public void setResidenceTown(Town residenceTown) {
        this.residenceTown = residenceTown;
    }

    @ManyToMany
    public Set<User> getUsersWhoAreFriendsOfUser() {
        return usersWhoAreFriendsOfUser;
    }

    public void setUsersWhoAreFriendsOfUser(Set<User> friends) {
        this.usersWhoAreFriendsOfUser = friends;
    }

    @ManyToMany(mappedBy = "usersWhoAreFriendsOfUser", cascade = CascadeType.ALL)
    public Set<User> getUsersWhoChoseUserAsFriend() {
        return usersWhoChoseUserAsFriend;
    }

    public void setUsersWhoChoseUserAsFriend(Set<User> usersWhoChoseUserAsFriend) {
        this.usersWhoChoseUserAsFriend = usersWhoChoseUserAsFriend;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
