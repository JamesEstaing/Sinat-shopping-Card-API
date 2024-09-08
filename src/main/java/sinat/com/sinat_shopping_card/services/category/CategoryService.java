package sinat.com.sinat_shopping_card.services.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sinat.com.sinat_shopping_card.entity.Category;
import sinat.com.sinat_shopping_card.exceptions.AlreadyExistExecption;
import sinat.com.sinat_shopping_card.exceptions.RessourceNotFoundExeption;
import sinat.com.sinat_shopping_card.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ImplCategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()->new RessourceNotFoundExeption("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(category1 -> !categoryRepository.existsByName(category1.getName() + "Category already exist "))
                .map(categoryRepository ::save)
                .orElseThrow(()-> new AlreadyExistExecption(category.getName() +" already existed."));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldcategory->{
            oldcategory.setName(category.getName());
            return categoryRepository.save(oldcategory);
        }).orElseThrow(()->new RessourceNotFoundExeption("Category not found"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository ::delete,
                        ()-> {
                    throw new RessourceNotFoundExeption("Category not found");
                        });

    }
}
