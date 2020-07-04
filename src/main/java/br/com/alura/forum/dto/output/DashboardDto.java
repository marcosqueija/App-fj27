package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardDto {

    private String categoryName;
    private List<String> subcategories;
    private Long allTopics;
    private Long lastWeekTopics;
    private Long unansweredTopics;

    public static DashboardDto of(Category category, Long allTopics, Long lastWeekTopics, Long unansweredTopics) {
        return new DashboardDto(category.getName(), category.getSubcategoryNames(), allTopics, lastWeekTopics, unansweredTopics);
    }

    public static Long getStatisticByCategory(List<TopicStatisticDto> countTopicsByCategory, Category category) {
        return countTopicsByCategory
                .stream()
                .filter(topicStatistic -> topicStatistic.getId().equals(category.getId()))
                .map(TopicStatisticDto::getQuantidade)
                .findAny()
                .orElse(0L);
    }

}
