package service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.forms.SubcategoryForm;
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
import java.util.Optional;

@Component
public class SubcategoryServiceImpl implements SubcategoryService{
    @Autowired
    private SubcategoriesRepository subcategoriesRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private AnswersRepository answersRepository;
    @Override
    public String getNameSubcatById(Long id){
        return subcategoriesRepository.findOne(id).getNameSubcat();
    }
    @Override
    public List<Subcategory> findAll(){
        return subcategoriesRepository.findAll();
    }
    @Override
    public Subcategory findOne(Long id){
        return subcategoriesRepository.findOne(id);
    }
    @Override
    public void save(SubcategoryForm subcategoryForm){
        Category category = categoriesRepository.findOne(subcategoryForm.getCategoryId());
        Subcategory subcategory = Subcategory.builder()
                .nameSubcat(subcategoryForm.getNameSubcat())
                .category(category)
                .build();
        subcategoriesRepository.save(subcategory);
    }
    @Override
    public void delete(Long id){
        Subcategory delSubcategory = subcategoriesRepository.findOne(id);
        List<Question> delQuestions = questionsRepository.findAllBySubcategory(delSubcategory);
        for (Question que: delQuestions){
            List<Answer> delAnswers = answersRepository.findAllByQuestion(que);
            for(Answer delAns: delAnswers){
                answersRepository.delete(delAns.getId());
            }
        }
        for(Question delQue: delQuestions){
            questionsRepository.delete(delQue.getId());
        }
        subcategoriesRepository.delete(id);
    }
    @Override
    public List<Subcategory> findAllByCategory(Category category){
        return subcategoriesRepository.findAllByCategory(category);
    }
}
