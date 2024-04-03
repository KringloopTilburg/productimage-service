package kringlooptilburg.nl.productimageservice.repositories;

import kringlooptilburg.nl.productimageservice.TestDataMongoUtil;
import kringlooptilburg.nl.productimageservice.domain.entities.PhotoMongo;
import kringlooptilburg.nl.productimageservice.repository.PhotoRepositoryMongo;
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
public class PhotoMongoRepositoryIntegrationTests {
    private PhotoRepositoryMongo photoRepositoryMongo;

    @Autowired
    public PhotoMongoRepositoryIntegrationTests(PhotoRepositoryMongo photoRepositoryMongo) {
        this.photoRepositoryMongo = photoRepositoryMongo;
}

//    @Test
//    public void testThatPhotoCanBeCreatedAndRecalled(){
//        PhotoMongo PhotoA = TestDataMongoUtil.createTestPhotoEntityA();
//        photoRepositoryMongo.save(PhotoA);
//        List<PhotoMongo> result = photoRepositoryMongo.findAll();
//        assertThat(result).contains(PhotoA);
//    }
}
