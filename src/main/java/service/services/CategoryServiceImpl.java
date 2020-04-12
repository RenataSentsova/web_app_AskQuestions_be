package service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.forms.CategoryForm;
import service.models.Answer;
import service.models.Category;
import service.models.Question;
import service.models.Subcategory;
import service.repositories.AnswersRepository;
import service.repositories.CategoriesRepository;
import service.repositories.QuestionsRepository;
import service.repositories.SubcategoriesRepository;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private SubcategoriesRepository subcategoriesRepository;
    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private AnswersRepository answersRepository;

    @Override
    public List<Category> findAll() {
        return categoriesRepository.findAllByOrderByIdAsc();
    }
    @Override
    public void save(CategoryForm categoryForm){
        Category category = Category.builder()
                .nameCategory(categoryForm.getNameCategory())
                .build();
        categoriesRepository.save(category);
    }
    @Override
    public void delete(Long categoryId){
        Category delCategory = categoriesRepository.findOne(categoryId);
        List<Subcategory> delSubcategories = subcategoriesRepository.findAllByCategory(delCategory);
        List<Question> delQuestions = new ArrayList<>();
        for (Subcategory sub: delSubcategories) {
            delQuestions.addAll(questionsRepository.findAllBySubcategory(sub));
        }
        for(Question delQue: delQuestions){
            List<Answer> delAnswers = answersRepository.findAllByQuestion(delQue);
            for(Answer delAns: delAnswers){
                answersRepository.delete(delAns.getId());
            }
            questionsRepository.delete(delQue.getId());
        }
        for (Subcategory delSub: delSubcategories) {
            subcategoriesRepository.delete(delSub.getId());
        }
        categoriesRepository.delete(categoryId);
    }
    @Override
    public Category findOne(Long id){
        return categoriesRepository.findOne(id);
    }
}
