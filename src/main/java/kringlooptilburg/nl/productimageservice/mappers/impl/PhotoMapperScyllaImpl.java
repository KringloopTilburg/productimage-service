package kringlooptilburg.nl.productimageservice.mappers.impl;

import kringlooptilburg.nl.productimageservice.domain.dto.PhotoScyllaDto;
import kringlooptilburg.nl.productimageservice.domain.dto.PhotoScyllaDto;
import kringlooptilburg.nl.productimageservice.domain.entities.PhotoScylla;
import kringlooptilburg.nl.productimageservice.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PhotoMapperScyllaImpl implements Mapper<PhotoScylla, PhotoScyllaDto> {
    private ModelMapper modelMapper;

    public PhotoMapperScyllaImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PhotoScyllaDto mapTo(PhotoScylla photo) {
        return modelMapper.map(photo, PhotoScyllaDto.class);
    }

    @Override
    public PhotoScylla mapFrom(PhotoScyllaDto photoDto) {
        return modelMapper.map(photoDto, PhotoScylla.class);
    }
}
