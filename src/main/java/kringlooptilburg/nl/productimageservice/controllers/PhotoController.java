package kringlooptilburg.nl.productimageservice.controllers;

import kringlooptilburg.nl.productimageservice.domain.dto.PhotoDto;
import kringlooptilburg.nl.productimageservice.domain.entities.Photo;
import kringlooptilburg.nl.productimageservice.mappers.Mapper;
import kringlooptilburg.nl.productimageservice.services.PhotoService;
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
@RequestMapping("/productimage-service")
public class PhotoController {
    private final PhotoService photoService;
    private final Mapper<Photo, PhotoDto> productMapper;

    @Autowired
    public PhotoController(PhotoService photoService, Mapper<Photo, PhotoDto> productMapper) {
        this.photoService = photoService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<String> addPhotoToProduct(@RequestPart("images") MultipartFile[] images, @RequestPart String productId) {
        try {
            for(var image : images){
                photoService.addPhoto(image.getOriginalFilename(), image.getBytes(), Integer.valueOf(productId));
            }

            return new ResponseEntity<>("Photos uploaded.", HttpStatus.CREATED);
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhotoDto> deletePhoto(@PathVariable String id) {
        photoService.deletePhoto(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> getPhoto(@PathVariable String id) {
        Optional<Photo> foundPhoto = photoService.getPhoto(id);
        return foundPhoto.map(photoScylla -> {
            PhotoDto photoDto = productMapper.mapTo(photoScylla);
            return new ResponseEntity<>(photoDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<String> getPhotos() {
        List<Photo> photos = photoService.findAll();
        ArrayList<String> photoStrings = new ArrayList<>();
        for (var photo : photos) {
            photoStrings.add("data:image/png;base64, " + photo.getBase64());
        }
        return photoStrings;
    }
}
