package kringlooptilburg.nl.productimageservice.services;

import kringlooptilburg.nl.productimageservice.domain.entities.Photo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface PhotoServiceMongo {
    String addPhoto(String originalFilename, MultipartFile image) throws IOException;
    List<Photo> findAll();
    Optional<Photo> getPhoto(String id);
    void deletePhoto(String id);
}
