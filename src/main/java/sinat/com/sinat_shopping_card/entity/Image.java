package sinat.com.sinat_shopping_card.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.sql.Blob;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String fileName;
private String fileType;

@Lob
private Blob image;
private String downloadUrl;

@ManyToOne
@JoinColumn(name = "product_Id")
private Product product;


}
