package com.api.investmanager.core.domain.model;

import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.exception.ClientException;

import java.math.BigDecimal;
import java.util.Objects;

public class Active {
    private String id;
    private String name;
    private final CategoryEnum category;
    private BigDecimal amount;

    public Active(CategoryEnum category, String name) {
        this.name = name;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ClientException("Quantidade não pode ser negativa");
        }
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Active active)) return false;
        return Objects.equals(id, active.id) && Objects.equals(name, active.name) && category == active.category && Objects.equals(amount, active.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, amount);
    }
}
