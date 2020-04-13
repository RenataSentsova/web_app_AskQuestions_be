package service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.models.Question;
import service.models.Subcategory;
import service.models.User;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByUser(User user);
    List<Question> findAllBySubcategory(Subcategory subcategory);
    List<Question> findAllBySubcategoryOrderByIdDesc(Subcategory subcategory);
}