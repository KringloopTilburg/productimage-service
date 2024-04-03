package kringlooptilburg.nl.productimageservice.mappers.impl;

import kringlooptilburg.nl.productimageservice.domain.dto.PhotoMongoDto;
import kringlooptilburg.nl.productimageservice.domain.entities.PhotoMongo;
import kringlooptilburg.nl.productimageservice.mappers.Mapper;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PhotoMapperMongoImpl implements Mapper<PhotoMongo, PhotoMongoDto> {
    private ModelMapper modelMapper;

    public PhotoMapperMongoImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PhotoMongoDto mapTo(PhotoMongo photoMongo) {
        return modelMapper.map(photoMongo, PhotoMongoDto.class);
    }

//    @Override
//    public PhotoMongo mapFrom(PhotoMongoDto photoDto) {
//        return modelMapper.map(photoDto, PhotoMongo.class);
//    }

    //    @Override
//    public PhotoMongo mapFrom(PhotoMongoDto photoDto) {
//        return modelMapper.map(photoDto, PhotoMongo.class);
//    }
    @Override
    public PhotoMongo mapFrom(PhotoMongoDto photoDto) throws IOException {
        PhotoMongo photoMongo = new PhotoMongo();
        photoMongo.setId(photoDto.getId());
        photoMongo.setProductId(photoDto.getProductId());
        photoMongo.setTitle(photoDto.getTitle());
        photoMongo.setPhoto((new Binary(BsonBinarySubType.BINARY,photoDto.getPhoto().getBytes())));
        return modelMapper.map(photoMongo, PhotoMongo.class);

//        return modelMapper.map(photoDto, PhotoMongo.class);
    }
}
