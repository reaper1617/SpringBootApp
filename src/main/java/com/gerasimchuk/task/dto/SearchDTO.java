package com.gerasimchuk.task.dto;

public class SearchDTO {
    private String searchText;

    public SearchDTO() {
    }

    public SearchDTO(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
