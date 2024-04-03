package kringlooptilburg.nl.productimageservice.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Blob;

@Table("photo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoScylla {
    @Id
    private String id;
    private String title;
    private String base64;
    private String productId;
}
