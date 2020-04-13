package service.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.models.Answer;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDto {
    private Long id;
    private String text;
    private Long userId;
    private Long questionId;
    private String modifiedOn;
    private String isBest;

    public static AnswerDto from(Answer answer){
        return AnswerDto.builder()
                .id(answer.getId())
                .text(answer.getText())
                .userId(answer.getUser().getId())
                .questionId(answer.getQuestion().getId())
                .modifiedOn(answer.getModifiedOn())
                .isBest(Boolean.toString(answer.isBest()))
                .build();
    }

    public static List<AnswerDto> from(List<Answer> answers) {
        return answers.stream().map(AnswerDto::from).collect(Collectors.toList());
    }
}
