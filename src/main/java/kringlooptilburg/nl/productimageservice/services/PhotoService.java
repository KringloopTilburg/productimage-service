package kringlooptilburg.nl.productimageservice.services;

import kringlooptilburg.nl.productimageservice.domain.entities.Photo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface PhotoService {
    String addPhoto(String originalFilename, MultipartFile image) throws IOException;
    List<Photo> findAll();
    Photo getPhoto(String id);
}
