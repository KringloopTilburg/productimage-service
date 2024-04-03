package kringlooptilburg.nl.productimageservice;

import kringlooptilburg.nl.productimageservice.domain.entities.PhotoMongo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

public class TestDataMongoUtil {
    private TestDataMongoUtil(){
    }

    public static PhotoMongo createTestPhotoEntityA() {
        return PhotoMongo.builder()
                .id("STRING_ID1")
                .productId("1")
                .photo(new Binary(BsonBinarySubType.BINARY, "abc".getBytes()))
                .title("Test Title A")
                .build();
    }
    public static PhotoMongo createTestPhotoEntityB() {
        return PhotoMongo.builder()
                .id("STRING_ID2")
                .productId("2")
                .photo(new Binary(BsonBinarySubType.BINARY, "xyz".getBytes()))
                .title("Test Title B")
                .build();
    }

}
