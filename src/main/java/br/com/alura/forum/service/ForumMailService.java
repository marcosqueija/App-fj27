package br.com.alura.forum.service;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class ForumMailService {

    private MailSender mailSender;

    @Async
    public void sendNewReplyMail(Answer answer) {
        Topic answeredTopic = answer.getTopic();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(answeredTopic.getOwnerEmail());
        message.setSubject("Novo comentário em: " + answeredTopic.getShortDescription());
        message.setText("Olá " + answeredTopic.getOwnerName() + "\n\n" + "Há uma nova mensagem no fórum!\n\n" +
                answer.getOwnerName() + " comentou no tópico: " + answeredTopic.getShortDescription());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("Erro ao enviar e-mail para" +	answer.getTopic().getOwnerEmail(), e.getMessage());
        }
    }

}
