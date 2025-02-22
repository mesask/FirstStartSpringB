package com.mesakh.firststartspringboot.service.impl;

import com.mesakh.firststartspringboot.constants.Constants;
import com.mesakh.firststartspringboot.models.Article;
import com.mesakh.firststartspringboot.repository.ArticleRepository;
import com.mesakh.firststartspringboot.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getArticles(){
        return articleRepository.findAllByStatus(Constants.STATUS_ACTIVE);
    }

    @Override
    public Article getArticlesById(int id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public void createAndUpdate(Article article) {
        articleRepository.save(article);
    }


}
