package service.services;

import service.forms.AnswerForm;
import service.models.Answer;
import service.models.Question;
import service.models.User;

import java.util.List;

public interface AnswerService {
    Answer findOne(Long id);
    List<Answer> findAll();
    List<Answer> findAllByUser(User user);
    List<Answer> findAllByQuestion(Question question);
    void save(AnswerForm answerForm);
    void delete(Long id);
    void changeBest(AnswerForm answerForm);
}
