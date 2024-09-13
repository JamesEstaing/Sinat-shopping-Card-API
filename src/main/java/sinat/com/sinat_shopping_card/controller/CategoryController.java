package sinat.com.sinat_shopping_card.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sinat.com.sinat_shopping_card.entity.Category;
import sinat.com.sinat_shopping_card.exceptions.RessourceNotFoundExeption;
import sinat.com.sinat_shopping_card.response.ApiResponse;
import sinat.com.sinat_shopping_card.services.category.ImplCategoryService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final ImplCategoryService categoryService;


    //Get all categories already registered
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {

        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found ", categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error ", INTERNAL_SERVER_ERROR));

        }
    }

    // Add a category
    @PostMapping("/add")
    // Add category
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try {
            Category thecategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Category added ", thecategory));
        } catch (RessourceNotFoundExeption e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }

    }

    // This endpoint allows you to get a category by its ID
    @GetMapping("/category/{id}/category")
    // Add category
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try {
            Category thecategory = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Found ", thecategory));
        } catch (RessourceNotFoundExeption e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }

    }

    // This endpoint allows you to get a category by its name
    @GetMapping("category/{name}/category")
    // Add category
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable String name) {
        try {
            Category thecategory = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Found ", thecategory));
        } catch (RessourceNotFoundExeption e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));

        }

    }

    // Delete a category by its ID
    @DeleteMapping("category/{id}/delete")
    // Add category
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Delete successfully ", null));
        } catch (RessourceNotFoundExeption e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }

    }

    // Update a category by its ID
    @PutMapping("category/{id}/update")
    // Add category
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category updateCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Update successfully ", updateCategory));
        } catch (RessourceNotFoundExeption e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }

    }

}
