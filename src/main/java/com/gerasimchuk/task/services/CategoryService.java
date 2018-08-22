package com.gerasimchuk.task.services;

import com.gerasimchuk.task.entities.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Integer id);
    Category getCategoryByName(String name);
    void saveCategory(Category category);
    void updateCategory(Integer id, String name, String description);
    void deleteCategory(Integer id);
    List<Category> findAll();
}
