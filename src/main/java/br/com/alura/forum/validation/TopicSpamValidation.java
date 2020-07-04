package br.com.alura.forum.validation;

import br.com.alura.forum.dto.input.CreateTopicDto;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
public class TopicSpamValidation implements Validator {

    private TopicService topicService;
    private User loggedUser;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CreateTopicDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Instant oneHourAgo = Instant.now().minus(1, ChronoUnit.HOURS);
        List<Topic> topics = topicService.findUserCreatedTopicsAfterInstant(loggedUser, oneHourAgo);
        if (topics.size() >= 4) {
            Instant instantOfTheOldestTopic = topics.get(0).getCreationInstant();
            long minutesToNextTopic = Duration.between(oneHourAgo, instantOfTheOldestTopic).getSeconds() / 60;
            errors.reject("newTopicInputDto.limit.exceeded", new Object[]{minutesToNextTopic},
                    "O limite individual de novos tópicos por hora foi excedido");
        }
    }

}
