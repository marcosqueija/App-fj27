package br.com.alura.forum.controller;

import br.com.alura.forum.dto.input.LoginDto;
import br.com.alura.forum.dto.output.AuthenticationTokenOutputDto;
import br.com.alura.forum.security.jwt.TokenManager;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.ok;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {

    private AuthenticationManager authManager;
    private TokenManager tokenManager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationTokenOutputDto> authenticate(@RequestBody LoginDto loginDto) {
        try {
            Authentication auth = authManager.authenticate(loginDto.toToken());
            return ok(new AuthenticationTokenOutputDto("Bearer", tokenManager.generateToken(auth)));
        } catch (AuthenticationException e) {
            return status(UNAUTHORIZED).build();
        }
    }

}
