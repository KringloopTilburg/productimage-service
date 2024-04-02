package kringlooptilburg.nl.productimageservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

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


}
