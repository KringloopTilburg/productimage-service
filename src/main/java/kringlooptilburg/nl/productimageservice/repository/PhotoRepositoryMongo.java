package kringlooptilburg.nl.productimageservice.repository;

import kringlooptilburg.nl.productimageservice.domain.entities.PhotoMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepositoryMongo extends MongoRepository<PhotoMongo, String> {
    Optional<PhotoMongo> findById(String id);
}
