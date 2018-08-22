package com.gerasimchuk.task.dto;

public class ChangedCategoryDTO {
    private int chosenCategoryId;
    private String name;
    private String description;

    public ChangedCategoryDTO() {
    }

    public ChangedCategoryDTO(int chosenCategoryId, String name, String description) {
        this.chosenCategoryId = chosenCategoryId;
        this.name = name;
        this.description = description;
    }

    public int getChosenCategoryId() {
        return chosenCategoryId;
    }

    public void setChosenCategoryId(int chosenCategoryId) {
        this.chosenCategoryId = chosenCategoryId;
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
}
