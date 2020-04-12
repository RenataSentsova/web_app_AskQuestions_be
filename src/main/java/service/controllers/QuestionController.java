package service.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.forms.QuestionForm;
import service.models.Category;
import service.models.Question;
import service.models.Subcategory;
import service.models.User;
import service.response.ResponseMessage;
import service.services.CategoryService;
import service.services.QuestionService;
import service.services.SubcategoryService;
import service.services.UsersService;
import service.transfer.QuestionDto;
import javax.validation.ValidationException;
import java.util.List;
import static service.transfer.QuestionDto.from;

@AllArgsConstructor
@CrossOrigin
@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/question/{questionid}")
    public QuestionDto findOne(@PathVariable Long questionid){
        return from(questionService.findOne(questionid));
    }

    @GetMapping("/allquestions")
    public List<QuestionDto> findAll(){
        return from(questionService.findAll());
    }

    @GetMapping("/questionsCount")
    public int findCount(){
        return questionService.findAll().size();
    }

    @GetMapping("/questions/bysubcategory/{subcategoryid}")
    public List<QuestionDto> bySubcategory(@PathVariable Long subcategoryid){
        Subcategory subcategory = subcategoryService.findOne(subcategoryid);
        return from(questionService.findAllBySubcategory(subcategory));
    }

    @GetMapping("/questionsbyuser/{userid}")
    public List<QuestionDto> byUser(@PathVariable Long userid){
        User user = usersService.findOne(userid);
        return from(questionService.findAllByUser(user));
    }

    @GetMapping("/qustionsbycategory/{categoryid}")
    public List<QuestionDto> byCategory(@PathVariable Long categoryid){
        Category category = categoryService.findOne(categoryid);
        return from(questionService.findAllByCategory(category));
    }

    @PostMapping("/ask")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionForm questionForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        questionService.save(questionForm);
        return new ResponseEntity<>(new ResponseMessage("Adding a question was successful"), HttpStatus.OK);
    }

    @GetMapping("/isHasBestAnswer/{questionid}")
    public boolean isHasBestAnswer(@PathVariable Long questionid){
        return questionService.isHasBestAnswer(questionid);
    }

    @GetMapping("/takeBestAnswerId/{questionid}")
    public Long takeBestAnswerId(@PathVariable Long questionid){
        return questionService.takeBestAnswerId(questionid);
    }

    @DeleteMapping("/editor/deletequestion/{id}")
    public void delete(@PathVariable Long id){
        questionService.delete(id);
    }
}
