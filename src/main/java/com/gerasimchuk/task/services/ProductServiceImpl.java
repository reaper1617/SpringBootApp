package com.gerasimchuk.task.services;

import com.gerasimchuk.task.entities.Category;
import com.gerasimchuk.task.entities.Product;
import com.gerasimchuk.task.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;


    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getProductById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public void saveProduct(Product product) {
        repository.save(product);
    }

    @Override
    public void updateProduct(Integer id, String name, String description) {
        Product updated = repository.findById(id).get();
        updated.setName(name);
        updated.setDescription(description);
        repository.save(updated);
    }

    @Override
    public void updateProduct(Integer id, String name, String description, Set<Category> categories) {
        Product updated = repository.findById(id).get();
        updated.setName(name);
        updated.setDescription(description);
        updated.setCategories(categories);
        repository.save(updated);
    }

    @Override
    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }
}
