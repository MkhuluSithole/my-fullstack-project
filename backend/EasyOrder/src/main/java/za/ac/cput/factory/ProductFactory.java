package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory {
    public Product createProduct(String name, String description, double price, byte[] imageBlob, Category category, boolean available) {
        return new Product.Builder()
            .name(name)
            .description(description)
            .price(price)
            .imageBlob(imageBlob)
            .category(category)
            .available(available)
            .build();
    }
}
