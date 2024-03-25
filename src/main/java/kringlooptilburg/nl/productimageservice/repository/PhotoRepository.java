package kringlooptilburg.nl.productimageservice.repository;

import kringlooptilburg.nl.productimageservice.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {
}
