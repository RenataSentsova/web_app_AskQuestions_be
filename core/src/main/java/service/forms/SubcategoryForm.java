package service.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.models.Category;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryForm {
    @NotNull
    private String nameSubcat;
    @NotNull
    private Long categoryId;
}
