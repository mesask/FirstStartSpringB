package com.mesakh.firststartspringboot.service.impl;

import com.mesakh.firststartspringboot.constants.Constants;
import com.mesakh.firststartspringboot.models.Category;
import com.mesakh.firststartspringboot.repository.CategoryRepository;
import com.mesakh.firststartspringboot.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllByStatus(Constants.STATUS_ACTIVE);
    }
}
