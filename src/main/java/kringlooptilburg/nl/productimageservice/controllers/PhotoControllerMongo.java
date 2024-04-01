package kringlooptilburg.nl.productimageservice.controllers;

import kringlooptilburg.nl.productimageservice.domain.entities.Photo;
import kringlooptilburg.nl.productimageservice.services.PhotoServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/productimage-service-mongo")
public class PhotoControllerMongo {
    @Autowired
    private PhotoServiceMongo photoServiceMongo;

    @PostMapping
    public String addPhoto(@RequestParam("image") MultipartFile image) throws IOException {
        String id = photoServiceMongo.addPhoto(image.getOriginalFilename(), image);
        return id;
    }
    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable String id) {
        photoServiceMongo.deletePhoto(id);
    }
    @GetMapping("/{id}")
    public String getPhoto(@PathVariable String id) {
        Photo photo = photoServiceMongo.getPhoto(id);
        byte[] bytes = photo.getPhoto().getData();
        String s = Base64.getEncoder().encodeToString(bytes);
        return "data:image/png;base64, " + s;
    }

    @GetMapping
    public List<String> getPhotos() {
        List<Photo> photos = photoServiceMongo.findAll();
        ArrayList<String> photoStrings = new ArrayList<>();
        for (var photo : photos) {
            byte[] bytes = photo.getPhoto().getData();
            String s = Base64.getEncoder().encodeToString(bytes);
            photoStrings.add("data:image/png;base64, " + s);
        }
        return photoStrings;
    }
}
