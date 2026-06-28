package za.ac.cput.service;

import za.ac.cput.domain.Product;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Long categoryId);
    List<Product> getAvailableProducts();
    void deleteProduct(Long id);
}
