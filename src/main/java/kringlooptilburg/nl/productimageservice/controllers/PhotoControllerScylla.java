package kringlooptilburg.nl.productimageservice.controllers;

import kringlooptilburg.nl.productimageservice.domain.entities.PhotoScylla;
import kringlooptilburg.nl.productimageservice.services.PhotoServiceScylla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/productimage-service-scylla")
public class PhotoControllerScylla {
    @Autowired
    private PhotoServiceScylla photoServiceScylla;

    @PostMapping
    public ResponseEntity<String> addPhoto(@RequestParam("image") MultipartFile image) {
        try {
            photoServiceScylla.addPhoto(image.getOriginalFilename(), image);
            return ResponseEntity.ok("Image uploaded successfully");
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }
    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable String id) {
        photoServiceScylla.deletePhoto(id);
    }
    @GetMapping("/{id}")
    public String getPhoto(@PathVariable String id) {
        PhotoScylla photo = photoServiceScylla.getPhoto(id);
        return "data:image/png;base64, " + photo.getBase64();
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
