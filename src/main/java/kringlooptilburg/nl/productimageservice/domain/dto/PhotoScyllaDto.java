package kringlooptilburg.nl.productimageservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoScyllaDto {
    private String id;
    private String title;
    private String base64;
    private String productId;
}
