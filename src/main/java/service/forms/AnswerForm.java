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
public class AnswerForm {
    private Long id;

    @NotEmpty(message = "Невозможно опубликовать пустой ответ")
    @Size(max = 200, min = 1, message = "Ответ не более 200 символов")
    private String text;

    @NotNull
    private Long userId;

    @NotNull
    private Long questionId;

    private String modifiedOn;
    private String isBest;
}
