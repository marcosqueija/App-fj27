package br.com.alura.forum.dto.input;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class LoginDto {

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken toToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}