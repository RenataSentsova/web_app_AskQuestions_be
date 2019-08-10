package service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.forms.AnswerForm;
import service.models.Answer;
import service.models.Question;
import service.models.User;
import service.repositories.AnswersRepository;
import service.repositories.QuestionsRepository;
import service.repositories.UserRepository;

import java.io.Console;
import java.util.List;

@Component
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswersRepository answersRepository;
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private QuestionsRepository questionsRepository;
    @Override
    public Answer findOne(Long id){
        return answersRepository.findOne(id);
    }
    @Override
    public List<Answer> findAll() {
        return answersRepository.findAll();
    }
    @Override
    public List<Answer> findAllByUser(User user) {
        return answersRepository.findAllByUser(user);
    }
    @Override
    public List<Answer> findAllByQuestion(Question question) {
        return answersRepository.findAllByQuestionOrderByIdAsc(question);
    }
    @Override
    public void save(AnswerForm answerForm){
        GetDate data = new GetDate();
        String modData = data.getDate();
        User user = usersRepository.findOne(answerForm.getUserId());
        Question question = questionsRepository.findOne(answerForm.getQuestionId());
        Answer answer = Answer.builder()
                .text(answerForm.getText())
                .user(user)
                .question(question)
                .modifiedOn(modData)
                .isBest(false)
                .build();
        answersRepository.save(answer);
    }
    @Override
    public void changeBest(AnswerForm answerForm){
        User user = usersRepository.findOne(answerForm.getUserId());
        Question question = questionsRepository.findOne(answerForm.getQuestionId());
        boolean isBest = false;
        if(answerForm.getIsBest().equals("true")) isBest = true;
        Answer changeAnswer = Answer.builder()
                .id(answerForm.getId())
                .text(answerForm.getText())
                .user(user)
                .question(question)
                .modifiedOn(answerForm.getModifiedOn())
                .isBest(isBest)
                .build();
        //answersRepository.delete(answerForm.getId());
        answersRepository.save(changeAnswer);
        question.setClosed(isBest);
        questionsRepository.save(question);
    }
    @Override
    public void delete(Long id) {
        answersRepository.delete(id);
    }
}
