package mockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import service.controllers.CategoryController;
import service.forms.CategoryForm;
import service.models.Category;
import service.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(categoryController)
                .build();
    }

    @Test
    public void findCount() throws Exception{
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(0L, "Dreams"));
        categories.add(new Category(1L, "Sport"));

        when(categoryService.findAll()).thenReturn(categories);

        mockMvc.perform(get("/categoriesCount"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", is(2)));

        verify(categoryService, times(1)).findAll();
    }

    @Test
    public void findAll() throws Exception{
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(0L, "Dreams"));
        categories.add(new Category(1L, "Sport"));
        when(categoryService.findAll()).thenReturn(categories);

        mockMvc.perform(get("/categories/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].nameCategory", is("Dreams")))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].nameCategory", is("Sport")));

        verify(categoryService, times(1)).findAll();
    }

    @Test
    public void addCategory() throws Exception{
        CategoryForm categoryForm = new CategoryForm("Games");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(categoryService).save(categoryForm);
        mockMvc.perform(post("/editor/addCategory")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(categoryForm)))
                .andDo(print());
        verify(categoryService, times(1)).save(categoryForm);
        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public void delete() throws Exception{
        Long id = 0L;

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/editor/deleteCategory/{id}", id))
            .andExpect(status().isOk());

        verify(categoryService, times(1)).delete(id);
        verifyNoMoreInteractions(categoryService);

    }

    // conversion from Java to JSON
    public static String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}