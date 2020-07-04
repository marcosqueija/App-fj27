package br.com.alura.forum.service;

import br.com.alura.forum.dto.input.AnswerInputDto;
import br.com.alura.forum.dto.output.AnswerOutputDto;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.AnswerRepository;
import br.com.alura.forum.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AnswerService {

    private AnswerRepository answerRepository;
    private TopicRepository topicRepository;
    private ForumMailService forumMailService;

    public AnswerOutputDto create(AnswerInputDto answerInputDto, Long topicId, User loggedUser) {
        Answer answer = new Answer(answerInputDto.getContent(), topicRepository
                .findById(topicId)
                .orElseThrow(NullPointerException::new), loggedUser);
        answer = answerRepository.save(answer);
        forumMailService.sendNewReplyMail(answer);
        return new AnswerOutputDto(answer);
    }

}
