package kringlooptilburg.nl.productimageservice;

import kringlooptilburg.nl.productimageservice.domain.entities.Photo;

public class TestDataUtil {

    private TestDataUtil(){
    }

    public static Photo createTestPhotoEntityA() {
        return Photo.builder()
                .id("STRING_ID1")
                .productId("1")
                .base64("base64abc")
                .title("Test Title A")
                .build();
    }
    public static Photo createTestPhotoEntityB() {
        return Photo.builder()
                .id("STRING_ID2")
                .productId("2")
                .base64("base64xyz")
                .title("Test Title B")
                .build();
    }
}
