package za.ac.cput.service;

import za.ac.cput.domain.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Category saveCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories();
    void deleteCategory(Long id);
}
