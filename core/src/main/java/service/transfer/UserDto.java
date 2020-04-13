package service.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String login;
    private String name;
    private String email;
    private int nbQuestions;
    private int nbAnswers;
    private String state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .nbQuestions(user.getQuestions().size())
                .nbAnswers(user.getAnswers().size())
                .state(user.getState())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
