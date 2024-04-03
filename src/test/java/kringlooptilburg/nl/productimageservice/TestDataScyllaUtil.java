package kringlooptilburg.nl.productimageservice;

import kringlooptilburg.nl.productimageservice.domain.entities.PhotoScylla;

public class TestDataScyllaUtil {

    private TestDataScyllaUtil(){
    }

    public static PhotoScylla createTestPhotoEntityA() {
        return PhotoScylla.builder()
                .id("STRING_ID1")
                .productId("1")
                .base64("base64abc")
                .title("Test Title A")
                .build();
    }
    public static PhotoScylla createTestPhotoEntityB() {
        return PhotoScylla.builder()
                .id("STRING_ID2")
                .productId("2")
                .base64("base64xyz")
                .title("Test Title B")
                .build();
    }
}
