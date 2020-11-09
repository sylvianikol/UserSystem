package com.springintro.usersystem.model.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.springintro.usersystem.constants.GlobalMessages.ALBUM_NAME_INVALID;

public class AlbumCreateDto {

    private String name;
    private String backgroundColor;
    private boolean isPublic;
    private UserDto userId;

    public AlbumCreateDto() {
    }

    public AlbumCreateDto(String name, String backgroundColor, boolean isPublic, UserDto userId) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.isPublic = isPublic;
        this.userId = userId;
    }

    @NotEmpty(message = "Album should have a name.")
    @NotNull(message = "Album could not be null!")
    @Size(min = 2, max = 50, message = ALBUM_NAME_INVALID)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public UserDto getUserId() {
        return userId;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }
}
