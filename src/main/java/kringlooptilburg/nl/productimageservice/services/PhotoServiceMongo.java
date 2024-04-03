package kringlooptilburg.nl.productimageservice.services;

import kringlooptilburg.nl.productimageservice.domain.entities.PhotoMongo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface PhotoServiceMongo {
    PhotoMongo addPhoto(String originalFilename, MultipartFile image) throws IOException;
    PhotoMongo addPhotoBody(PhotoMongo photoMongo);

    List<PhotoMongo> findAll();
    Optional<PhotoMongo> getPhoto(String id);
    void deletePhoto(String id);
}
