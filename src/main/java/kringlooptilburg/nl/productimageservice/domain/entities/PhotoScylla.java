package kringlooptilburg.nl.productimageservice.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Blob;

@Table("photo")
@Getter
@Setter
public class PhotoScylla {
    @Id
    private String id;
    private String title;
    private String base64;
}
