package com.gerasimchuk.task.services;

import com.gerasimchuk.task.entities.Category;
import com.gerasimchuk.task.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CriteriaService {


    private EntityManager entityManager;
    private ProductService productService;

    @Autowired
    public CriteriaService(EntityManager entityManager, ProductService productService) {
        this.entityManager = entityManager;
        this.productService = productService;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Product> executeQuery(String search){

        CriteriaBuilder criteriaBuilder =  entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = productCriteria.from(Product.class);
        productCriteria.select(productRoot).where(criteriaBuilder.like(productRoot.get("name"),"%"+search+"%"));
        List<Product> result = entityManager.createQuery(productCriteria).getResultList();

        CriteriaQuery<Category> categoryCriteria = criteriaBuilder.createQuery(Category.class);
        Root<Category> categoryRoot = categoryCriteria.from(Category.class);
        categoryCriteria.select(categoryRoot).where(criteriaBuilder.like(categoryRoot.get("name"), "%"+search+"%"));
        List<Category> result2 = entityManager.createQuery(categoryCriteria).getResultList();

        List<Product> allProducts = productService.findAll();
        for(Product p: allProducts){
            for(Category c: result2) {
                if (p.getCategories().contains(c) && !result.contains(p)) result.add(p);
            }
        }
        return  result;
    }

}
