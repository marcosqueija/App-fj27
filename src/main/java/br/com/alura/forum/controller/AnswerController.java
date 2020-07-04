package br.com.alura.forum.controller;

import br.com.alura.forum.dto.input.AnswerInputDto;
import br.com.alura.forum.dto.output.AnswerOutputDto;
import br.com.alura.forum.model.User;
import br.com.alura.forum.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/topics/{topicId}/answers")
public class AnswerController {

    private AnswerService answerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AnswerOutputDto answerTopic(@PathVariable Long topicId,
                                       @Valid @RequestBody AnswerInputDto answerInputDto,
                                       @AuthenticationPrincipal User loggedUser) {
        return answerService.create(answerInputDto, topicId, loggedUser);
    }

}
