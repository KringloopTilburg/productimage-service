package kringlooptilburg.nl.productimageservice.repositories;

import kringlooptilburg.nl.productimageservice.TestDataScyllaUtil;
import kringlooptilburg.nl.productimageservice.domain.entities.PhotoScylla;
import kringlooptilburg.nl.productimageservice.repository.PhotoRepositoryScylla;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PhotoScyllaRepositoryIntegrationTests {

    private PhotoRepositoryScylla photoRepositoryScylla;
    @Autowired
    public PhotoScyllaRepositoryIntegrationTests(PhotoRepositoryScylla photoRepositoryScylla) {
        this.photoRepositoryScylla = photoRepositoryScylla;
    }

//    @Test
//    public void testThatPhotoCanBeCreatedAndRecalled(){
//        PhotoScylla PhotoA = TestDataScyllaUtil.createTestPhotoEntityA();
//        photoRepositoryScylla.save(PhotoA);
//        List<PhotoScylla> result = photoRepositoryScylla.findAll();
//        assertThat(result).contains(PhotoA);
//    }
}
