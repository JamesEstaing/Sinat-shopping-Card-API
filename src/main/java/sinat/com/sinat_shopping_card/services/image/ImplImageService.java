package sinat.com.sinat_shopping_card.services.image;

import org.springframework.web.multipart.MultipartFile;
import sinat.com.sinat_shopping_card.dto.ImageDto;
import sinat.com.sinat_shopping_card.entity.Image;

import java.util.List;

public interface ImplImageService {
    Image getImageById(Long id);
    void deleteImageById(Long imageId);


    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
