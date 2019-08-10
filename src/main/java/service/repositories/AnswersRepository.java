package service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.models.Answer;
import service.models.Question;
import service.models.User;

import java.util.List;

@Repository
public interface AnswersRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByUser(User user);
    List<Answer> findAllByQuestionOrderByIdAsc(Question question);
    List<Answer> findAllByQuestion(Question question);
}
