package sinat.com.sinat_shopping_card.services.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sinat.com.sinat_shopping_card.dto.ImageDto;
import sinat.com.sinat_shopping_card.entity.Image;
import sinat.com.sinat_shopping_card.entity.Product;
import sinat.com.sinat_shopping_card.exceptions.RessourceNotFoundExeption;
import sinat.com.sinat_shopping_card.repository.ImageRepository;
import sinat.com.sinat_shopping_card.services.product.IProductService;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements ImplImageService {

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundExeption("No image found by id " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new RessourceNotFoundExeption("Image not found with this id " + id);
        });

    }

//    @Override
//    public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
//        return null;
//    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> saveImageDto = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

// Returned the Url of the image that you want to download, not the image itself.
                // Created a path to download the image
                String buildDownloadUrl = "/Api/v1/images/image/download/" + image.getId();
                String downloadUrl = buildDownloadUrl + image.getImage();
                image.setDownloadUrl(downloadUrl);
                Image saveImage = imageRepository.save(image);

                // Modify URL
                saveImage.setDownloadUrl(buildDownloadUrl + saveImage.getId());
                imageRepository.save(saveImage);

// Selected what to return to the user
                ImageDto imageDto = new ImageDto();
                imageDto.setImageId(saveImage.getId());
                imageDto.setImageName(saveImage.getFileName());
                imageDto.setDownloadUrl(saveImage.getDownloadUrl());
                saveImageDto.add(imageDto);


            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());

            }

        }
        return saveImageDto;
        // imageRepository.save(image)
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }


    }
}
