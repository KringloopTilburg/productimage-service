package kringlooptilburg.nl.productimageservice.repository;

import kringlooptilburg.nl.productimageservice.domain.entities.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepositoryMongo extends MongoRepository<Photo, String> {
}
