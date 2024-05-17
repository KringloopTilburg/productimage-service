package kringlooptilburg.nl.productimageservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kringlooptilburg.nl.productimageservice.repository.PhotoRepository;
import kringlooptilburg.nl.productimageservice.services.PhotoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestConfiguration {

    @Bean
    public CassandraOperations cassandraTemplate() {
        return mock(CassandraOperations.class);
    }

    @Bean
    public PhotoService photoService() {
        return mock(PhotoService.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return mock(ObjectMapper.class);
    }

    @Bean
    public PhotoRepository photoRepository() {
        return mock(PhotoRepository.class);
    }
}