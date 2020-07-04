package br.com.alura.forum.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class CreateTopicDto {

    @NotBlank
    @Size(min = 10, max = 123123)
    private	String shortDescription;

    private	String content;

    @NotBlank
    private	String courseName;

}