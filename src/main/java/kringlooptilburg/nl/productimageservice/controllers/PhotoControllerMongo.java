package kringlooptilburg.nl.productimageservice.controllers;

import kringlooptilburg.nl.productimageservice.domain.dto.PhotoDto;
import kringlooptilburg.nl.productimageservice.domain.entities.Photo;
import kringlooptilburg.nl.productimageservice.mappers.Mapper;
import kringlooptilburg.nl.productimageservice.services.PhotoServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productimage-service-mongo")
public class PhotoControllerMongo {

    private final PhotoServiceMongo photoServiceMongo;
    private final Mapper<Photo, PhotoDto> productMapper;

    @Autowired
    public PhotoControllerMongo(PhotoServiceMongo photoServiceMongo, Mapper<Photo, PhotoDto> productMapper) {
        this.photoServiceMongo = photoServiceMongo;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<String> addPhoto(@RequestParam("image") MultipartFile image) throws IOException {
        try {
            String id = photoServiceMongo.addPhoto(image.getOriginalFilename(), image);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhotoDto> deletePhoto(@PathVariable String id) throws HttpClientErrorException.NotFound {
        photoServiceMongo.deletePhoto(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> getPhoto(@PathVariable String id) {
        Optional<Photo> foundPhoto = photoServiceMongo.getPhoto(id);
        return foundPhoto.map(photo -> {
            PhotoDto photoDto = productMapper.mapTo(photo);
            return new ResponseEntity<>(photoDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<PhotoDto> getPhotos() {
        List<Photo> photos = photoServiceMongo.findAll();
        return photos.stream()
                .map(productMapper::mapTo)
                .collect(Collectors.toList());
    }
}
