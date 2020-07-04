package br.com.alura.forum.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AnswerInputDto {

    @NotBlank
    @Size(min = 5)
    private String content;

}
