package com.springintro.usersystem.services.impl;

import com.springintro.usersystem.constants.Command;
import com.springintro.usersystem.io.OutputWriter;
import com.springintro.usersystem.model.dtos.AlbumCreateDto;
import com.springintro.usersystem.model.entities.Album;
import com.springintro.usersystem.repositories.AlbumRepository;
import com.springintro.usersystem.services.AlbumService;
import com.springintro.usersystem.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.springintro.usersystem.constants.Command.BACK;
import static com.springintro.usersystem.constants.Command.START;
import static com.springintro.usersystem.constants.GlobalMessages.ALBUM_CREATED;
import static com.springintro.usersystem.constants.GlobalMessages.ALBUM_EXISTS;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final OutputWriter writer;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository,
                            ModelMapper modelMapper,
                            ValidationUtil validationUtil,
                            OutputWriter writer) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.writer = writer;
    }

    @Override
    public Command createAlbum(AlbumCreateDto albumCreateDto) {
        if (!this.validationUtil.isValid(albumCreateDto)) {
            this.writer.writeLine(this.validationUtil.getViolations(albumCreateDto));
            return START;
        }

        String albumName = albumCreateDto.getName();

        if (existsAlbum(albumName)) {
            this.writer.writeLine(String.format(ALBUM_EXISTS, albumName));
            return START;
        }

        Album album = this.modelMapper.map(albumCreateDto, Album.class);

        this.albumRepository.saveAndFlush(album);

        this.writer.writeLine(String.format(ALBUM_CREATED, albumName));

        return BACK;
    }

    private boolean existsAlbum(String name) {
        return this.albumRepository.findByName(name).orElse(null) != null;
    }
}
