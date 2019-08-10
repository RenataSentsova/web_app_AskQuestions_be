package service.services;

import org.springframework.stereotype.Component;
import service.forms.QuestionForm;
import service.models.*;
import service.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {
    private QuestionsRepository questionsRepository;
    private SubcategoriesRepository subcategoriesRepository;
    private UserRepository usersRepository;
    private AnswersRepository answersRepository;

    public QuestionServiceImpl(QuestionsRepository questionsRepository, SubcategoriesRepository subcategoriesRepository, UserRepository usersRepository, AnswersRepository answersRepository) {
        this.questionsRepository = questionsRepository;
        this.subcategoriesRepository = subcategoriesRepository;
        this.usersRepository = usersRepository;
        this.answersRepository = answersRepository;
    }

    @Override
    public Question findOne(Long id) {
        return questionsRepository.findOne(id);
    }

    @Override
    public List<Question> findAll(){
        return questionsRepository.findAll();
    }

    @Override
    public List<Question> findAllByUser(User user){
        return questionsRepository.findAllByUser(user);
    }

    @Override
    public List<Question> findAllBySubcategory(Subcategory subcategory) {
        return questionsRepository.findAllBySubcategoryOrderByIdDesc(subcategory);
    }

    @Override
    public List<Question> findAllByCategory(Category category) {
        List<Subcategory> subcategories = subcategoriesRepository.findAllByCategory(category);
        List<Question> questions = new ArrayList<>();
        for (Subcategory subcategory: subcategories){
            questions.addAll(questionsRepository.findAllBySubcategory(subcategory));
        }
        return questions;
    }

    @Override
    public void save(QuestionForm questionForm) {
        GetDate data = new GetDate();
        String modData = data.getDate();
        User user = usersRepository.findOne(questionForm.getUserId());
        Subcategory subcategory = subcategoriesRepository.findOne(questionForm.getSubcategoryId());
        Question question = Question.builder()
                .topic(questionForm.getTopic())
                .text(questionForm.getText())
                .modifiedOn(modData)
                .user(user)
                .subcategory(subcategory)
                .isClosed(false)
                .build();
        questionsRepository.save(question);
    }

    @Override
    public void delete(Long id) {
        Question delQuestion = questionsRepository.findOne(id);
        List<Answer> delAnswers = answersRepository.findAllByQuestion(delQuestion);
        for(Answer delAns: delAnswers){
            answersRepository.delete(delAns.getId());
        }
        questionsRepository.delete(id);
    }

    @Override
    public boolean isHasBestAnswer(Long id){
        Question question = questionsRepository.findOne(id);
        List<Answer> answers = answersRepository.findAllByQuestion(question);
        for(Answer ans: answers){
            if(ans.isBest()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Long takeBestAnswerId(Long questionId){
        Question question = questionsRepository.findOne(questionId);
        List<Answer> answers = answersRepository.findAllByQuestion(question);
        for(Answer ans: answers){
            if(ans.isBest()) {
                return ans.getId();
            }
        }
        return null;
    }
}
