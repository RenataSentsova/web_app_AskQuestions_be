package service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.models.Category;
import service.models.Question;
import service.models.Subcategory;

import java.util.List;

@Repository
public interface SubcategoriesRepository extends JpaRepository<Subcategory, Long> {
    List<Subcategory> findAllByCategory(Category category);
}
