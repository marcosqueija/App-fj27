package br.com.alura.forum.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationTokenOutputDto {

    private String tokenType;
    private String token;

}
