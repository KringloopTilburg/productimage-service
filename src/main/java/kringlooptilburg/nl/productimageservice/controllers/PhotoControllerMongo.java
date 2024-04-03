package kringlooptilburg.nl.productimageservice.controllers;

import kringlooptilburg.nl.productimageservice.domain.dto.PhotoMongoDto;
import kringlooptilburg.nl.productimageservice.domain.entities.PhotoMongo;
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
    private final Mapper<PhotoMongo, PhotoMongoDto> photoMapper;

    @Autowired
    public PhotoControllerMongo(PhotoServiceMongo photoServiceMongo, Mapper<PhotoMongo, PhotoMongoDto> photoMapper) {
        this.photoServiceMongo = photoServiceMongo;
        this.photoMapper = photoMapper;
    }

    @PostMapping
    public ResponseEntity<String> addPhoto(@RequestParam("image") MultipartFile image) throws IOException {
        try {
            photoServiceMongo.addPhoto(image.getOriginalFilename(), image);
            return new ResponseEntity<>("Photo created.", HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }
    @PostMapping("/phototest")
    public ResponseEntity<PhotoMongoDto> addPhotoBody(@RequestBody PhotoMongoDto photoMongoDto) throws IOException {
            PhotoMongo photoMongo = photoMapper.mapFrom(photoMongoDto);
            photoServiceMongo.addPhotoBody(photoMongo);
            return new ResponseEntity<>(photoMapper.mapTo(photoMongo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhotoMongoDto> deletePhoto(@PathVariable String id) throws HttpClientErrorException.NotFound {
        photoServiceMongo.deletePhoto(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoMongoDto> getPhoto(@PathVariable String id) {
        Optional<PhotoMongo> foundPhoto = photoServiceMongo.getPhoto(id);
        return foundPhoto.map(photo -> {
            PhotoMongoDto photoDto = photoMapper.mapTo(photo);
            return new ResponseEntity<>(photoDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<PhotoMongoDto> getPhotos() {
        List<PhotoMongo> photoMongos = photoServiceMongo.findAll();
        return photoMongos.stream()
                .map(photoMapper::mapTo)
                .collect(Collectors.toList());
    }
}
