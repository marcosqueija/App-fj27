package br.com.alura.forum.service;

import br.com.alura.forum.dto.output.DashboardDto;
import br.com.alura.forum.dto.output.TopicStatisticDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.repository.CategoryRepository;
import br.com.alura.forum.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static br.com.alura.forum.dto.output.DashboardDto.getStatisticByCategory;

@AllArgsConstructor
@Service
public class DashboardService {

    private TopicRepository topicRepository;
    private CategoryRepository categoryRepository;

    public List<DashboardDto> getDashboardStatistics() {
        List<DashboardDto> categoriesDto = new ArrayList<>();

        List<Category> categories = categoryRepository.findByCategoryIsNull();

        List<TopicStatisticDto> countTopicsByCategory = topicRepository.findCountTopicsGroupByCategory();

        List<TopicStatisticDto> countTopicsFromLastWeekByCategory =
                topicRepository.findCountTopicsFromInstantGroupByCategory(Instant.now().minus(Duration.ofDays(7)));

        List<TopicStatisticDto> countTopicsNotAnsweredByCategory =
                topicRepository.findCountTopicsNotAnsweredGroupByCategory();

        categories.forEach(category -> {
            Long allTopics = getStatisticByCategory(countTopicsByCategory, category);
            Long lastWeekTopics = getStatisticByCategory(countTopicsFromLastWeekByCategory, category);
            Long notAnsweredTopics = getStatisticByCategory(countTopicsNotAnsweredByCategory, category);

            categoriesDto.add(DashboardDto.of(category, allTopics, lastWeekTopics, notAnsweredTopics));
        });

        return categoriesDto;
    }

}
