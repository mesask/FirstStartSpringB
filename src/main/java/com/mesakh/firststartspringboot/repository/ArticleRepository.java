package com.mesakh.firststartspringboot.repository;

import com.mesakh.firststartspringboot.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    List<Article> findAllByStatus(String status);
}
