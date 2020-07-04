package br.com.alura.forum.mail;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic.domain.Topic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Component
public class NewReplyMailTemplate {

    private TemplateEngine templateEngine;

    public String getTemplate(Answer answer) {
        Topic answeredTopic = answer.getTopic();

        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("topicOwnerName", answeredTopic.getOwnerName());
        thymeleafContext.setVariable("topicShortDescription", answeredTopic.getShortDescription());
        thymeleafContext.setVariable("answerAuthor", answer.getOwnerName());
        thymeleafContext.setVariable("answerCreationInstant", getFormattedCreationTime(answer));
        thymeleafContext.setVariable("answerContent", answer.getContent());

        return templateEngine.process("email-template.html", thymeleafContext);
    }

    private String getFormattedCreationTime(Answer answer) {
        return DateTimeFormatter.ofPattern("kk:mm")
                .withZone(ZoneId.of("America/Sao_Paulo"))
                .format(answer.getCreationTime());
    }

}