package service.services;

import service.forms.SubcategoryForm;
import service.models.Category;
import service.models.Subcategory;

import java.util.List;

public interface SubcategoryService {
    String getNameSubcatById(Long id);
    List<Subcategory> findAll();
    Subcategory findOne(Long id);
    void save(SubcategoryForm subcategoryForm);
    void delete(Long id);
    List<Subcategory> findAllByCategory(Category category);
}
