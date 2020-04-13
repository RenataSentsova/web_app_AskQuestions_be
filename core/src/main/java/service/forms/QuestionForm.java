package service.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionForm {
    @NotEmpty(message = "Заполните тему вопроса")
    @Size(max = 255, min=10, message = "Должно быть больше 10 символов. Не более 255 символов.")
    private String topic;
    @Size(max = 255,  message = "Не более 255 символов.")
    private String text;
    @NotNull
    private Long userId;
    @NotNull
    private Long subcategoryId;
}
