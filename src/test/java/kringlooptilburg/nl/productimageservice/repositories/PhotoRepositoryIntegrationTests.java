package kringlooptilburg.nl.productimageservice.repositories;

import kringlooptilburg.nl.productimageservice.repository.PhotoRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class PhotoRepositoryIntegrationTests {

    private PhotoRepository photoRepository;
    @Autowired
    public PhotoRepositoryIntegrationTests(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

//    @Test
//    public void testThatPhotoCanBeCreatedAndRecalled(){
//        PhotoScylla PhotoA = TestDataScyllaUtil.createTestPhotoEntityA();
//        photoRepositoryScylla.save(PhotoA);
//        List<PhotoScylla> result = photoRepositoryScylla.findAll();
//        assertThat(result).contains(PhotoA);
//    }
}
