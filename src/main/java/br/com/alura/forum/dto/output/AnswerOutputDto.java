package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.Answer;
import lombok.Data;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class AnswerOutputDto {

    private Long id;
    private String content;
    private Instant creationTime;
    private boolean solution;
    private String ownerName;

    public AnswerOutputDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.creationTime = answer.getCreationTime();
        this.solution = answer.isSolution();
        this.ownerName = answer.getOwnerName();
    }

    public static List<AnswerOutputDto> ofAnswers(List<Answer> answers) {
        return answers.stream()
                .map(AnswerOutputDto::new)
                .collect(toList());
    }

}
