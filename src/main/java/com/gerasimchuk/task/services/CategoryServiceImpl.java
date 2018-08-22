package com.gerasimchuk.task.services;

import com.gerasimchuk.task.entities.Category;
import com.gerasimchuk.task.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public Category getCategoryById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Category getCategoryByName(String name) {
        List<Category> categories = repository.findAll();
        for(Category c: categories){
            if (c.getName().equals(name)) return c;
        }
        return null;
    }

    @Override
    public void saveCategory(Category category) {
        repository.save(category);
    }

    @Override
    public void updateCategory(Integer id, String name, String description) {
        Category updated = repository.findById(id).get();
        updated.setName(name);
        updated.setDescription(description);
        repository.save(updated);
    }

    @Override
    public void deleteCategory(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();

    }
}
