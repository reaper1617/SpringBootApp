package com.gerasimchuk.task.services;

import com.gerasimchuk.task.entities.Category;
import com.gerasimchuk.task.entities.Product;
import com.gerasimchuk.task.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface ProductService {
    Product getProductById(Integer id);
    void saveProduct(Product product);
    void updateProduct(Integer id, String name, String description);
    void updateProduct(Integer id, String name, String description, Set<Category> categories);
    void deleteProduct(Integer id);
    List<Product> findAll();
}

