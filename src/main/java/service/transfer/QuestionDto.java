package service.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.models.Question;
import service.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {
    private Long id;
    private String topic;
    private String text;
    private Long userId;
    private String modifiedOn;
    private int nbAnswers;
    private String isClosed;

    public static QuestionDto from(Question question){
        return QuestionDto.builder()
                .id(question.getId())
                .topic(question.getTopic())
                .text(question.getText())
                .userId(question.getUser().getId())
                .modifiedOn(question.getModifiedOn())
                .nbAnswers(question.getAnswers().size())
                .isClosed(Boolean.toString(question.isClosed()))
                .build();
    }

    public static List<QuestionDto> from(List<Question> questions) {
        return questions.stream().map(QuestionDto::from).collect(Collectors.toList());
    }
}
