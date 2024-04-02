package kringlooptilburg.nl.productimageservice.controllers;

import kringlooptilburg.nl.productimageservice.domain.dto.PhotoDto;
import kringlooptilburg.nl.productimageservice.domain.dto.PhotoScyllaDto;
import kringlooptilburg.nl.productimageservice.domain.entities.Photo;
import kringlooptilburg.nl.productimageservice.domain.entities.PhotoScylla;
import kringlooptilburg.nl.productimageservice.mappers.Mapper;
import kringlooptilburg.nl.productimageservice.services.PhotoServiceMongo;
import kringlooptilburg.nl.productimageservice.services.PhotoServiceScylla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productimage-service-scylla")
public class PhotoControllerScylla {
    private final PhotoServiceScylla photoServiceScylla;
    private final Mapper<PhotoScylla, PhotoScyllaDto> productMapper;

    @Autowired
    public PhotoControllerScylla(PhotoServiceScylla photoServiceScylla, Mapper<PhotoScylla, PhotoScyllaDto> productMapper) {
        this.photoServiceScylla = photoServiceScylla;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<String> addPhoto(@RequestParam("image") MultipartFile image) {
        try {
            photoServiceScylla.addPhoto(image.getOriginalFilename(), image);
            return new ResponseEntity<>("Photo uploaded.", HttpStatus.CREATED);
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PhotoScyllaDto> deletePhoto(@PathVariable String id) {
        photoServiceScylla.deletePhoto(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PhotoScyllaDto> getPhoto(@PathVariable String id) {
        Optional<PhotoScylla> foundPhoto = photoServiceScylla.getPhoto(id);
        return foundPhoto.map(photoScylla -> {
            PhotoScyllaDto photoScyllaDto = productMapper.mapTo(photoScylla);
            return new ResponseEntity<>(photoScyllaDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        return "data:image/png;base64, " + photo.getBase64();
    }

    @GetMapping
    public List<String> getPhotos() {
        List<PhotoScylla> photos = photoServiceScylla.findAll();
        ArrayList<String> photoStrings = new ArrayList<>();
        for (var photo : photos) {
            photoStrings.add("data:image/png;base64, " + photo.getBase64());
        }
        return photoStrings;
    }
}
