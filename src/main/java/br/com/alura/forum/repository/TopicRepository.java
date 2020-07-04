package br.com.alura.forum.repository;

import br.com.alura.forum.dto.output.TopicStatisticDto;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {

    List<Topic>	findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(User loggedUser, Instant oneHourAgo);

    @Query("SELECT " +
            "   new br.com.alura.forum.dto.output.TopicStatisticDto(category.id, category.name, COUNT(t.id)) " +
            "FROM " +
            "   Topic t " +
            "   join t.course c " +
            "   join c.subcategory subCategory " +
            "   join subCategory.category category " +
            "GROUP BY " +
            "   category.id")
    List<TopicStatisticDto> findCountTopicsGroupByCategory();

    @Query("SELECT " +
            "   new br.com.alura.forum.dto.output.TopicStatisticDto(category.id, category.name, COUNT(t.id)) " +
            "FROM " +
            "   Topic t " +
            "   join t.course c " +
            "   join c.subcategory subCategory " +
            "   join subCategory.category category " +
            "WHERE " +
            "   t.status = br.com.alura.forum.model.topic.domain.TopicStatus.NOT_ANSWERED " +
            "GROUP BY " +
            "   category.id")
    List<TopicStatisticDto> findCountTopicsNotAnsweredGroupByCategory();

    @Query("SELECT " +
            "   new br.com.alura.forum.dto.output.TopicStatisticDto(category.id, category.name, COUNT(t.id)) " +
            "FROM " +
            "   Topic t " +
            "   join t.course c " +
            "   join c.subcategory subCategory " +
            "   join subCategory.category category " +
            "WHERE " +
            "   t.creationInstant >= :instant " +
            "GROUP BY " +
            "   category.id")
    List<TopicStatisticDto> findCountTopicsFromInstantGroupByCategory(@Param("instant") Instant instant);

}