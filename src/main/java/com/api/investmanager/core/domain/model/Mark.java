package com.api.investmanager.core.domain.model;

import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.exception.ClientException;

import java.util.Objects;

public class Mark {
    private final String id;

    private Integer percentage;

    private final CategoryEnum category;

    public Mark(String id, CategoryEnum category) {
        this.id = id;
        this.category = category;
    }

    public Mark(String id, Integer percentage, CategoryEnum category) {
        this.id = id;
        this.category = category;
        this.percentage = percentage;
    }

    public String getId() {
        return id;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        if (percentage < 0) {
            throw new ClientException("A porcentagem não pode ser inferior a 0%.");
        }
        if(percentage > 100) {
            throw new ClientException("A porcentagem não pode ser superior a 100%.");
        }
        this.percentage = percentage;
    }

    public CategoryEnum getCategory() {
        return category;
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
