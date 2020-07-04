package br.com.alura.forum.dto.output;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorsOutputDto {

    private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();
    private List<String> globalErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
        fieldErrors.add(fieldError);
    }

    public void addGlobalError(String message) {
        globalErrors.add(message);
    }

    public int getNumberOfErrors() {
        return fieldErrors.size() + globalErrors.size();
    }

}
