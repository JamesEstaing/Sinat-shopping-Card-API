package sinat.com.sinat_shopping_card.service.product;

import lombok.RequiredArgsConstructor;
import sinat.com.sinat_shopping_card.entity.Category;
import sinat.com.sinat_shopping_card.entity.Product;
import sinat.com.sinat_shopping_card.exceptions.ProductNotFoundException;
import sinat.com.sinat_shopping_card.repository.CategoryRepository;
import sinat.com.sinat_shopping_card.repository.ProductRepository;
import sinat.com.sinat_shopping_card.request.AddProductRequest;
import sinat.com.sinat_shopping_card.request.UpdateProductRequest;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName());

                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));

    }

    private Product createProduct(AddProductRequest request, Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProviderNotFoundException("Message not found"));

    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,
                ()-> { throw new ProviderNotFoundException("Product not found");
        });

    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateProduct(existingProduct, request))
                .map(productRepository :: save)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));

    }

    private Product UpdateExistingProduct(Product existingProduct, UpdateProductRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public List<Product> countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
