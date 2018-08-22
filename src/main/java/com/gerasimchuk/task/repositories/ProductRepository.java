package com.gerasimchuk.task.repositories;

import com.gerasimchuk.task.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
