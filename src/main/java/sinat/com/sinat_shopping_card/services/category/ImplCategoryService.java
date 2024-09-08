package sinat.com.sinat_shopping_card.services.category;

import sinat.com.sinat_shopping_card.entity.Category;

import java.util.List;

public interface ImplCategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategory(Long id);


}
