package com.gerasimchuk.task.dto;

public class ProductDTO {
    private String name;
    private String description;
    private String[] categories;

    public ProductDTO() {
    }

    public ProductDTO(String name, String description, String[] categories) {
        this.name = name;
        this.description = description;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
