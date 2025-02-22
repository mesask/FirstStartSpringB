package com.mesakh.firststartspringboot.controller.admin;

import com.mesakh.firststartspringboot.constants.Constants;
import com.mesakh.firststartspringboot.models.Article;
import com.mesakh.firststartspringboot.models.response.KeyValueItem;
import com.mesakh.firststartspringboot.repository.CategoryRepository;
import com.mesakh.firststartspringboot.repository.PositionRepository;
import com.mesakh.firststartspringboot.service.ArticleService;
import com.mesakh.firststartspringboot.service.CategoryService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

@Controller
public class ArticleController {
    private final ArticleService articleService;
//    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public ArticleController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
//        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/articles")
    public String index(Model model) {
        model.addAttribute("articles",articleService.getArticles());
        return "/admin/article/index";
    }

    @GetMapping("/admin/articles/add")
    public String add(Model model) {
        var article = new Article();
        article.setCategoryList(categoryService.getAllCategories());
        article.setStatusList(KeyValueItem.getAllStatus());
        model.addAttribute("article",article);
        return "/admin/article/form";
    }

    @GetMapping("/admin/articles/edit/{id}")
    public String add(Model model,@PathVariable("id") int id) {
        var article = articleService.getArticlesById(id);
        article.setCategoryList(categoryService.getAllCategories());
        article.setStatusList(KeyValueItem.getAllStatus());
        model.addAttribute("article",article);
        return "/admin/article/form";
    }

    @PostMapping("/admin/articles/create")
    public String create(@ModelAttribute("article") Article article) {
        articleService.createAndUpdate(article);
        return "redirect:/admin/articles";
    }

    @GetMapping("/admin/articles/delete/{id}")
    public String delete(@PathVariable("id") int id){
        var article  = articleService.getArticlesById(id);
        if(article != null){
            article.setStatus(Constants.STATUS_DELETE);
            articleService.createAndUpdate(article);
        }
        return "redirect:/admin/articles";
    }
}
