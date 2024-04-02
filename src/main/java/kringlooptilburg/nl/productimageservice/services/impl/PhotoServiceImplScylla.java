package kringlooptilburg.nl.productimageservice.services.impl;

import kringlooptilburg.nl.productimageservice.domain.entities.PhotoScylla;
import kringlooptilburg.nl.productimageservice.repository.PhotoRepositoryScylla;
import kringlooptilburg.nl.productimageservice.services.PhotoServiceScylla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoServiceImplScylla implements PhotoServiceScylla {
    @Autowired
    private PhotoRepositoryScylla photoRepository;

    @Override
    public PhotoScylla addPhoto(String originalFilename, MultipartFile file) throws IOException {
        PhotoScylla photo = new PhotoScylla();
        photo.setTitle(originalFilename);
        photo.setId(UUID.randomUUID().toString());
        photo.setBase64(Base64.getEncoder().encodeToString(file.getBytes()));
        return photoRepository.save(photo);
    }

    @Override
    public void deletePhoto(String id) {
        photoRepository.delete(photoRepository.findById(id).get());
    }
    @Override
    public List<PhotoScylla> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Optional<PhotoScylla> getPhoto(String id) {
        return photoRepository.findById(id);
    }
}
