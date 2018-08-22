package com.gerasimchuk.task.controllers;

import com.gerasimchuk.task.dto.*;
import com.gerasimchuk.task.entities.Category;
import com.gerasimchuk.task.entities.Product;
import com.gerasimchuk.task.services.CategoryService;
import com.gerasimchuk.task.services.CriteriaService;
import com.gerasimchuk.task.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private CriteriaService criteriaService;


    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, CriteriaService criteriaService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.criteriaService = criteriaService;
    }

    //    @GetMapping("/")
//    public String index(Model ui){
//        return "catalog";
//    }


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model ui){
        List<Product> products = productService.findAll();
        ui.addAttribute("products", products);
        return "index";
    }

    @RequestMapping(value = {"/index/{id}"}, method = RequestMethod.POST)
    public String indexPost(@PathVariable("id") int id, ChosenProductDTO chosenProductDTO, BindingResult bindingResult, Model ui){
        if (id == 1) {
            if (chosenProductDTO != null) {
                ui.addAttribute("chosenProductId", chosenProductDTO.getChosenProductId());
                List<Category> categories = categoryService.findAll();
                ui.addAttribute("categories", categories);
                return "changeproduct";
            }
        }
        if (id == 2){
            if (chosenProductDTO!=null) {
                productService.deleteProduct(chosenProductDTO.getChosenProductId());
                List<Product> products = productService.findAll();
                ui.addAttribute("products", products);
                return "index";
            }
        }
        return "error";
    }


    @RequestMapping(value = "/addnewproduct", method = RequestMethod.GET)
    public String addNewProduct(Model ui){

        List<Category> categories = categoryService.findAll();
        ui.addAttribute("categories", categories);
        return "addnewproduct";
    }

    @RequestMapping(value = "/addnewproduct", method = RequestMethod.POST)
    public String addNewProductPost(ProductDTO product, BindingResult bindingResult, Model ui){
        if (product!=null){
            Product newProduct = new Product(product.getName(),product.getDescription());
            Set<Category> categories = new HashSet<>();
            for(int i=0; i < product.getCategories().length; i++){
                Category c = categoryService.getCategoryByName(product.getCategories()[i]);
                categories.add(c);
                c.addProductToSet(newProduct); // not saved yet??
            }
            newProduct.setCategories(categories);
            productService.saveProduct(newProduct);
            List<Product> products = productService.findAll();
            ui.addAttribute("products", products);
            return "index";
        }
        return "error";
    }

    @RequestMapping(value = "/changeproduct", method = RequestMethod.GET)
    public String changeProduct(Model ui){
        return "changeproduct";
    }

    @RequestMapping(value = "/changeproduct", method = RequestMethod.POST)
    public String changeProductPost(ChangedProductDTO changedProductDTO, BindingResult bindingResult, Model ui){
        if (changedProductDTO!=null){

            Product edited = productService.getProductById(changedProductDTO.getChosenProductId());
            String newName = edited.getName();
            String newDescr = edited.getDescription();
            Set<Category> newCategories = edited.getCategories();

            if (changedProductDTO.getName()!=null && changedProductDTO.getName().length()!=0) newName = changedProductDTO.getName();
            if (changedProductDTO.getDescription()!=null && changedProductDTO.getDescription().length()!=0) newDescr=changedProductDTO.getDescription();
            if (changedProductDTO.getCategories()!=null && changedProductDTO.getCategories().length!=0){

                Set<Category> categories = new HashSet<>();
                for(int i=0; i < changedProductDTO.getCategories().length; i++){
                    Category c = categoryService.getCategoryByName(changedProductDTO.getCategories()[i]);
                    categories.add(c);
                }
                newCategories.clear();
                newCategories = categories;
            }

            productService.updateProduct(changedProductDTO.getChosenProductId(),newName,newDescr,newCategories);
            List<Product> products = productService.findAll();
            ui.addAttribute("products", products);
            return "index";
        }
        return "error";
    }

    @RequestMapping(value = "/addnewcategory", method = RequestMethod.GET)
    public String addNewCategory(Model ui){
        List<Category> categories = categoryService.findAll();
        ui.addAttribute("categories", categories);
        return "addnewcategory";
    }

    @RequestMapping(value = "/addnewcategory/{id}", method = RequestMethod.POST)
    public String addNewCategoryPost(@PathVariable("id") int id, ChangedCategoryDTO changedCategoryDTO, BindingResult bindingResult, Model ui) {
        if (id == 1) {
            if (changedCategoryDTO != null) {
                Category category = new Category(changedCategoryDTO.getName(), changedCategoryDTO.getDescription());
                categoryService.saveCategory(category);
                List<Category> categories = categoryService.findAll();
                ui.addAttribute("categories", categories);
                return "addnewcategory";
            }
        }
        if (id == 2){
            if (changedCategoryDTO!=null) {
                ui.addAttribute("chosenCategoryId", changedCategoryDTO.getChosenCategoryId());
                return "changecategory";
            }
        }
        if (id ==3){
                if (changedCategoryDTO!=null){
                    Category deleted = categoryService.getCategoryById(changedCategoryDTO.getChosenCategoryId());

                    List<Product> products = productService.findAll();
                    for(Product p: products){
                        Set<Category> categories = p.getCategories();
                        Set<Category> newCategories = new HashSet<>();
                        for(Category c: categories){
                            if (! c.getName().equals(deleted.getName())) newCategories.add(c);
                        }
                        p.setCategories(newCategories);
                    }

                    categoryService.deleteCategory(changedCategoryDTO.getChosenCategoryId());
                    List<Category> categories = categoryService.findAll();
                    ui.addAttribute("categories", categories);
                    return "addnewcategory";
                }
        }
        return "error";
    }

    @RequestMapping(value = "/changecategory", method = RequestMethod.GET)
    public String changeCategory(Model ui){
        return "changecategory";
    }

    @RequestMapping(value = "/changecategory", method = RequestMethod.POST)
    public String changeCategoryPost(ChangedCategoryDTO changedCategoryDTO, BindingResult bindingResult, Model ui){
        if (changedCategoryDTO!=null){
            Category category = categoryService.getCategoryById(changedCategoryDTO.getChosenCategoryId());


            String newName = category.getName();
            String newDescr = category.getDescription();

            if (changedCategoryDTO.getName()!=null && changedCategoryDTO.getName().length()!=0) newName= changedCategoryDTO.getName();
            if (changedCategoryDTO.getDescription()!=null && changedCategoryDTO.getDescription().length()!=0) newDescr = changedCategoryDTO.getDescription();

            categoryService.updateCategory(changedCategoryDTO.getChosenCategoryId(), newName, newDescr);

            List<Category> categories = categoryService.findAll();
            ui.addAttribute("categories", categories);
            return "addnewcategory";
        }

        return "error";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model ui){
        return "search";
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchPost(SearchDTO searchDTO, BindingResult bindingResult, Model ui){
        if (searchDTO!=null) {
            List<Product> products = criteriaService.executeQuery(searchDTO.getSearchText());
            ui.addAttribute("products", products);
            return "search";
        }
        return "error";
    }

}
