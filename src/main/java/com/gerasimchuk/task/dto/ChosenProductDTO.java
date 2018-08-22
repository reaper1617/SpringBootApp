package com.gerasimchuk.task.dto;

public class ChosenProductDTO {
    private int chosenProductId;

    public ChosenProductDTO() {
    }

    public ChosenProductDTO(int chosenProductId) {
        this.chosenProductId = chosenProductId;
    }

    public int getChosenProductId() {
        return chosenProductId;
    }

    public void setChosenProductId(int chosenProductId) {
        this.chosenProductId = chosenProductId;
    }
}
