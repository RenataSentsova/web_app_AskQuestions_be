package service.services;

import service.forms.QuestionForm;
import service.models.Category;
import service.models.Question;
import service.models.Subcategory;
import service.models.User;

import java.util.List;

public interface QuestionService {
    Question findOne(Long id);
    List<Question> findAll();
    List<Question> findAllByUser(User user);
    List<Question> findAllBySubcategory(Subcategory subcategory);
    List<Question> findAllByCategory(Category category);
    void save(QuestionForm questionForm);
    void delete(Long id);
    boolean isHasBestAnswer(Long id);
    Long takeBestAnswerId(Long questionId);
}
