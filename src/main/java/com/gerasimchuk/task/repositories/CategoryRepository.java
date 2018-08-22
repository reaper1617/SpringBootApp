package com.gerasimchuk.task.repositories;

import com.gerasimchuk.task.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
