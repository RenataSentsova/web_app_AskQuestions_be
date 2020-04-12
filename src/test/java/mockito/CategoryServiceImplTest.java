package mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.forms.CategoryForm;
import service.models.*;
import service.repositories.AnswersRepository;
import service.repositories.CategoriesRepository;
import service.repositories.QuestionsRepository;
import service.repositories.SubcategoriesRepository;
import service.services.CategoryServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {
    @Mock
    CategoriesRepository categoriesRepository;
    @Mock
    SubcategoriesRepository subcategoriesRepository;
    @Mock
    QuestionsRepository questionsRepository;
    @Mock
    AnswersRepository answersRepository;

    @InjectMocks
    CategoryServiceImpl categoryService;

    private List<Category> categoryList = new ArrayList<>();

    @Before
    public void CategoryServiceImplTestSetUp(){
        Category cat1 = new Category(0L, "Games");
        Category cat2 = new Category(1L, "Kids");
        Category cat3 = new Category(3L, "Movies");
        categoryList.add(cat1);
        categoryList.add(cat2);
        categoryList.add(cat3);
    }

    @Test
    public void findAll() {
        when(categoriesRepository.findAllByOrderByIdAsc()).thenReturn(categoryList);
        assertEquals(categoryList, categoryService.findAll());
    }

    @Test
    public void save() {
        CategoryForm categoryForm = new CategoryForm("Family");
        Category category = Category.builder()
                .nameCategory(categoryForm.getNameCategory())
                .build();
        categoryService.save(categoryForm);
        verify(categoriesRepository, times(1)).save(category);
    }

    @Test
    public void delete() {
        Long categoryId = 0L;
        Category delCat = new Category(0L, "Games");
        when(categoriesRepository.findOne(categoryId)).thenReturn(delCat);

        // creating List delSubcategories
        Subcategory subcat1 = new Subcategory(0L, "Games1", delCat);
        List<Subcategory> subcategoryList = new ArrayList<>();
        subcategoryList.add(subcat1);
        when(subcategoriesRepository.findAllByCategory(delCat)).thenReturn(subcategoryList);

        // creating List delQuestions
        User user1 = new User(0L, "active", "u1", "u1", "u1@g.com", "123456", null);
        Question q1 = new Question(0L, "1", "1", false, "", user1, subcat1  , null);
        List<Question> delQuestions = new ArrayList<>();
        delQuestions.add(q1);
        // creating List delAnswers
        Answer a1 = new Answer(0L, "1", "11", false, user1, q1);
        List<Answer> delAnswers = new ArrayList<>();
        delAnswers.add(a1);
        when(answersRepository.findAllByQuestion(q1)).thenReturn(delAnswers);
        when(questionsRepository.findAllBySubcategory(subcat1)).thenReturn(delQuestions);

        categoryService.delete(categoryId);
        verify(answersRepository, times(1)).delete(a1.getId());
        verify(questionsRepository, times(1)).delete(q1.getId());
        verify(subcategoriesRepository, times(1)).delete(subcat1.getId());
        verify(categoriesRepository, times(1)).delete(categoryId);

    }

    @Test
    public void findOne() {
        Long categoryId = 0L;
        Category category = new Category(0L, "Games");
        when(categoriesRepository.findOne(categoryId)).thenReturn(category);
        assertEquals(category, categoryService.findOne(categoryId));
    }
}