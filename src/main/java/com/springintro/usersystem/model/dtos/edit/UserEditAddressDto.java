package com.springintro.usersystem.model.dtos.edit;

import com.springintro.usersystem.model.dtos.TownIdDto;

public class UserEditAddressDto {

    private Long id;
    private TownIdDto homeTown;
    private TownIdDto residenceTown;

    public UserEditAddressDto() {
    }

    public UserEditAddressDto(Long id, TownIdDto homeTown, TownIdDto residenceTown) {
        this.id = id;
        this.homeTown = homeTown;
        this.residenceTown = residenceTown;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TownIdDto getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(TownIdDto homeTown) {
        this.homeTown = homeTown;
    }

    public TownIdDto getResidenceTown() {
        return residenceTown;
    }

    public void setResidenceTown(TownIdDto residenceTown) {
        this.residenceTown = residenceTown;
    }
}
