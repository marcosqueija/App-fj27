package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TopicOutputDto {

    private Integer id;
    private String shortDescription;
    private long secondsSinceLastUpdate;
    private String ownerName;
    private String courseName;
    private String subcategoryName;
    private String categoryName;
    private int numberOfResponses;
    private boolean solved;
    private TopicStatus topicStatus;
    private List<AnswerOutputDto> answers;

    public static TopicOutputDto of(Topic topic) {
        return new TopicOutputDto(topic);
    }

    public static List<TopicOutputDto> ofTopics(List<Topic> topics) {
        return topics.stream()
                .map(TopicOutputDto::new)
                .collect(Collectors.toList());
    }

    public static Page<TopicOutputDto> ofTopics(Page<Topic> topics) {
        return topics.map(TopicOutputDto::new);
    }

    private TopicOutputDto(Topic topic) {
        this.id = topic.getId().intValue();
        this.shortDescription = topic.getShortDescription();
        this.secondsSinceLastUpdate = getSecondsSince(topic.getLastUpdate());
        this.ownerName = topic.getOwner().getName();
        this.courseName = topic.getCourse().getName();
        this.subcategoryName = topic.getCourse().getSubcategoryName();
        this.categoryName = topic.getCourse().getCategoryName();
        this.numberOfResponses = topic.getNumberOfAnswers();
        this.solved = TopicStatus.SOLVED.equals(topic.getStatus());
        this.topicStatus = topic.getStatus();
        this.answers = AnswerOutputDto.ofAnswers(topic.getAnswers());
    }

    private long getSecondsSince(Instant lastUpdate) {
        return Duration.between(lastUpdate, Instant.now()).get(ChronoUnit.SECONDS);
    }

}