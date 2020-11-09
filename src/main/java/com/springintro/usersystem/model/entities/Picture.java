package com.springintro.usersystem.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    private String title;
    private String caption;
    private String path;

    private Set<Album> albums;

    public Picture() {
        this.albums = new HashSet<>();
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Column
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pictures_albums",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
