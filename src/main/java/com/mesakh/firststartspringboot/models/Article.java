package com.mesakh.firststartspringboot.models;

import com.mesakh.firststartspringboot.models.response.KeyValueItem;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @ManyToOne
    private Category category;
    private String status;
    @Transient
    private List<Category> categoryList;
    @Transient
    private List<KeyValueItem> statusList;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<KeyValueItem> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<KeyValueItem> statusList) {
        this.statusList = statusList;
    }


}
