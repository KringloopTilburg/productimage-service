package kringlooptilburg.nl.productimageservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoMongoDto {
    private String id;
    private String title;
    private MultipartFile photo;
    private String productId;
}
