package br.com.alura.forum.service;

import br.com.alura.forum.dto.input.CreateTopicDto;
import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicOutputDto;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static br.com.alura.forum.dto.output.TopicOutputDto.ofTopics;

@AllArgsConstructor
@Service
public class TopicService {

    private TopicRepository topicRepository;
    private CourseRepository courseRepository;

    public Page<TopicOutputDto> getTopics(TopicSearchDto topicSearchDto, Pageable pageable) {
        return ofTopics(topicRepository.findAll(topicSearchDto.toSpecification(), pageable));
    }

    public TopicOutputDto createTopic(CreateTopicDto createTopicDto, User loggedUser) {
        Course course = courseRepository
                .findByName(createTopicDto.getCourseName())
                .orElseThrow(() -> new NullPointerException("Curso '"+ createTopicDto.getCourseName() +"' não foi encontrado"));
        Topic topic = new Topic(createTopicDto.getShortDescription(), createTopicDto.getContent(), loggedUser, course);
        return TopicOutputDto.of(topicRepository.save(topic));
    }

    public List<Topic> findUserCreatedTopicsAfterInstant(User loggedUser, Instant oneHourAgo) {
        return topicRepository.findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(loggedUser, oneHourAgo);
    }

    public TopicOutputDto getTopic(Long id) {
        return TopicOutputDto.of(
                topicRepository
                        .findById(id)
                        .orElseThrow(NullPointerException::new));
    }
}
