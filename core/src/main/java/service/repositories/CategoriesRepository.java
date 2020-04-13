package service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.models.Category;
import java.util.Optional;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByIdAsc();
}