package br.com.alura.forum.dto.input;

import lombok.Data;

@Data
public class CreateTopicDto {

    private	String shortDescription;
    private	String content;
    private	String courseName;

}