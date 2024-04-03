package kringlooptilburg.nl.productimageservice.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import kringlooptilburg.nl.productimageservice.TestDataMongoUtil;
import kringlooptilburg.nl.productimageservice.domain.entities.PhotoMongo;
import kringlooptilburg.nl.productimageservice.services.PhotoServiceMongo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PhotoMongoRepositoryIntegrationTests {
    private PhotoServiceMongo photoServiceMongo;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public PhotoMongoRepositoryIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, PhotoServiceMongo photoServiceMongo) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.photoServiceMongo = photoServiceMongo;
    }

    @Test
    public void testThatAddedPhotoMongoSuccessfullyReturnsHttp201Created() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("image", "filename.jpg", "image/jpeg", "content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/productimage-service-mongo")
                .file(multipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatGetPhotoSuccessfullyReturnsHttp200OkWhenProductExists() throws Exception {
        PhotoMongo photoMongo = new PhotoMongo();
        MockMultipartFile image = new MockMultipartFile("image", "filename.jpg", "image/jpeg", "content".getBytes());
        photoMongo.setTitle("Epic title");
        photoMongo.setId("UUID");
        photoMongo.setPhoto(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        photoServiceMongo.addPhoto("EpicFileName", image);

        mockMvc.perform(MockMvcRequestBuilders.get("/productimage-service-mongo/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}
