package kringlooptilburg.nl.productimageservice.repository;

import kringlooptilburg.nl.productimageservice.domain.entities.PhotoScylla;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepositoryScylla extends CassandraRepository<PhotoScylla, String> {
    Optional<PhotoScylla> findById(String id);
}
