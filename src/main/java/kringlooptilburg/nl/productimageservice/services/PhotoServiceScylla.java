package kringlooptilburg.nl.productimageservice.services;

import kringlooptilburg.nl.productimageservice.domain.entities.PhotoScylla;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public interface PhotoServiceScylla {

    PhotoScylla addPhoto(String originalFilename, MultipartFile file) throws IOException;

    List<PhotoScylla> findAll();

    PhotoScylla getPhoto(String id);

    void deletePhoto(String id);
}
