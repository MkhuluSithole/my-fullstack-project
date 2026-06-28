package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CategoryFactory {
    public Category createCategory(String name, List<Product> products) {
        return new Category.Builder()
            .name(name)
            .products(products)
            .build();
    }
}
