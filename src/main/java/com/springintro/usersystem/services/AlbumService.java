package com.springintro.usersystem.services;

import com.springintro.usersystem.constants.Command;
import com.springintro.usersystem.model.dtos.AlbumCreateDto;

public interface AlbumService {

    Command createAlbum(AlbumCreateDto albumCreateDto);
}
