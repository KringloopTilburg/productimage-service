package kringlooptilburg.nl.productimageservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PhotoControllerScyllaIntegrationTest {
    private PhotoControllerScylla photoController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public PhotoControllerScyllaIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, PhotoControllerScylla photoController) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.photoController = photoController;
    }

    @Test
    public void testThatAddedPhotoSuccessfullyReturnsHttp201Created() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("image", "filename.jpg", "image/jpeg", "content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/productimage-service-scylla")
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

        mockMvc.perform(MockMvcRequestBuilders.get("/productimage-service-scylla")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}
