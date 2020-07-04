package br.com.alura.forum.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FieldErrorOutputDto {

    private	String field;
    private	String message;

}
