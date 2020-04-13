package service.services;

import service.forms.CategoryForm;
import service.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    void save(CategoryForm categoryForm);
    void delete(Long id);
    Category findOne(Long categoryId);
}
