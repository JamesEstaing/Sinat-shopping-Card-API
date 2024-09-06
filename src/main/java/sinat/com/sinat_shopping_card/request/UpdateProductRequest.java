package sinat.com.sinat_shopping_card.request;


import lombok.Data;
import sinat.com.sinat_shopping_card.entity.Category;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
