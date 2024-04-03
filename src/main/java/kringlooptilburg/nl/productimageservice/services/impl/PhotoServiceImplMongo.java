package kringlooptilburg.nl.productimageservice.services.impl;

import kringlooptilburg.nl.productimageservice.domain.entities.PhotoMongo;
import kringlooptilburg.nl.productimageservice.repository.PhotoRepositoryMongo;
import kringlooptilburg.nl.productimageservice.services.PhotoServiceMongo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImplMongo implements PhotoServiceMongo {
    @Autowired
    private PhotoRepositoryMongo photoRepository;

    @Override
    public PhotoMongo addPhoto(String originalFilename, MultipartFile image) throws IOException {
        PhotoMongo photoMongo = new PhotoMongo();
        photoMongo.setTitle(originalFilename);
        photoMongo.setPhoto(new Binary(BsonBinarySubType.BINARY,image.getBytes()));
        return photoRepository.save(photoMongo);
    }
    @Override
    public PhotoMongo addPhotoBody(PhotoMongo photoMongo) {
        return photoRepository.save(photoMongo);
    }

    @Override
    public void deletePhoto(String id) {
        photoRepository.delete(photoRepository.findById(id).get());
    }
    @Override
    public List<PhotoMongo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Optional<PhotoMongo> getPhoto(String id) {
        return photoRepository.findById(id);
    }
}