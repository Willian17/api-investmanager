package com.api.investmanager.core.domain.model;

import com.api.investmanager.core.domain.enuns.CategoryEnum;

import java.util.Objects;

public class Mark {
    private String id;

    private Integer percentage;

    private CategoryEnum category;

    public Mark(String id, Integer percentage, CategoryEnum category) {
        this.id = id;
        this.percentage = percentage;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark mark)) return false;
        return Objects.equals(id, mark.id) && Objects.equals(percentage, mark.percentage) && category == mark.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, percentage, category);
    }
}
