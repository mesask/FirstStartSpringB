package com.mesakh.firststartspringboot.controller.admin;

import com.mesakh.firststartspringboot.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/admin/articles")
    public String index(Model model) {
        model.addAttribute("articles",articleService.getArticles());
        return "/admin/article/index";
    }
}
