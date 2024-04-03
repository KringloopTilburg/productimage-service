package kringlooptilburg.nl.productimageservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kringlooptilburg.nl.productimageservice.domain.entities.PhotoMongo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.DataInput;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PhotoControllerMongoIntegrationTest {
    private PhotoControllerMongo photoController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public PhotoControllerMongoIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, PhotoControllerMongo photoController) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.photoController = photoController;
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
    public void testThatGetPhotoSuccessfullyReturnsHttp200OkWhenPhotoExists() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "filename.jpg", "image/jpeg", "content".getBytes());
        photoController.addPhoto(image);

        mockMvc.perform(MockMvcRequestBuilders.get("/productimage-service-mongo")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}
