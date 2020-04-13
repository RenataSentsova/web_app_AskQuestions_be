package service.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.forms.CategoryForm;
import service.services.CategoryService;
import service.transfer.CategoryDto;
import javax.validation.ValidationException;
import java.util.List;
import static service.transfer.CategoryDto.from;

@AllArgsConstructor
@CrossOrigin
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categoriesCount")
    public int findCount(){
        return categoryService.findAll().size();
    }

    @GetMapping("/categories/all")
    public List<CategoryDto> findAll(){
        return from(categoryService.findAll());
    }

    @PostMapping("/editor/addCategory")
    public ResponseEntity<Object> addCategory(@RequestBody CategoryForm categoryForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        categoryService.save(categoryForm);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/editor/deleteCategory/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
