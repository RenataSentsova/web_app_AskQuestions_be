package service.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.forms.SubcategoryForm;
import service.models.Category;
import service.services.CategoryService;
import service.services.SubcategoryService;
import service.transfer.SubcategoryDto;
import javax.validation.ValidationException;
import java.util.List;
import static service.transfer.SubcategoryDto.from;

@AllArgsConstructor
@CrossOrigin
@RestController
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/subcategoriesCount")
    public int findCount(){
        return subcategoryService.findAll().size();
    }

    @GetMapping("/subcategories/{id}")
    public String getNameSubcatById(@PathVariable Long id){
        return subcategoryService.getNameSubcatById(id);
    }

    @GetMapping("/subcategories/all")
    public List<SubcategoryDto> findAll(){
        return from(subcategoryService.findAll());
    }

    @GetMapping("/subcategories/bycategory/{categoryid}")
    public List<SubcategoryDto> byCategory(@PathVariable Long categoryid){
        Category category = categoryService.findOne(categoryid);
        return from(subcategoryService.findAllByCategory(category));
    }

    @PostMapping("/editor/addsubcategory")
    public ResponseEntity<Object> addSubcategory(@RequestBody SubcategoryForm subcategoryForm,
                                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        subcategoryService.save(subcategoryForm);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/editor/deletesubcategory/{id}")
    public void delete(@PathVariable Long id){
        subcategoryService.delete(id);
    }
}
