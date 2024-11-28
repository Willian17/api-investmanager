package com.api.investmanager.core.domain.model;

import com.api.investmanager.core.domain.enuns.CategoryEnum;

import java.util.Objects;

public class Question {
    private final String id;
    private String question;
    private String criterion;
    private final CategoryEnum category;

    public Question(String id,String question, String criterion, CategoryEnum category) {
        this.id = id;
        this.question = question;
        this.criterion = criterion;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question1)) return false;
        return Objects.equals(question, question1.question) && Objects.equals(criterion, question1.criterion) && category == question1.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, criterion, category);
    }
}
