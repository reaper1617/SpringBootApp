package com.gerasimchuk.task.dto;

public class ChangedProductDTO {
    private int chosenProductId;
    private String name;
    private String description;
    private String[] categories;

    public ChangedProductDTO() {
    }

    public ChangedProductDTO(int chosenProductId, String name, String description, String[] categories) {
        this.chosenProductId = chosenProductId;
        this.name = name;
        this.description = description;
        this.categories = categories;
    }

    public int getChosenProductId() {
        return chosenProductId;
    }

    public void setChosenProductId(int chosenProductId) {
        this.chosenProductId = chosenProductId;
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
