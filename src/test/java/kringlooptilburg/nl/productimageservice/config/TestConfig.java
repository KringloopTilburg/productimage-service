package kringlooptilburg.nl.productimageservice.config;

import kringlooptilburg.nl.productimageservice.repository.PhotoRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    @Primary
    public PhotoRepository photoRepository() {
        return Mockito.mock(PhotoRepository.class);
    }
}
