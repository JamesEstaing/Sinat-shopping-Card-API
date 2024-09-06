package sinat.com.sinat_shopping_card.service.product;

import sinat.com.sinat_shopping_card.entity.Product;
import sinat.com.sinat_shopping_card.request.AddProductRequest;
import sinat.com.sinat_shopping_card.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product getProductById (Long id);
    void deleteProductById (Long id);
    Product updateProduct(UpdateProductRequest request, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String  name);
    List<Product> countProductByBrandAndName(String brand, String name);


}
