package sinat.com.sinat_shopping_card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sinat.com.sinat_shopping_card.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);
    
    List<Product> findByName(String name);

    List<Product> findByBrandAndName(String brand, String name);

    List<Product> countByBrandAndName(String brand, String name);
}
