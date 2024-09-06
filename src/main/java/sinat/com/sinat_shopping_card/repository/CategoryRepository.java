package sinat.com.sinat_shopping_card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sinat.com.sinat_shopping_card.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
