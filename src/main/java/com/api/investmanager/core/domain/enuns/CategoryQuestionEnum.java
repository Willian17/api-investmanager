package com.api.investmanager.core.domain.enuns;

public enum CategoryQuestionEnum {
    ACOES_NACIONAIS("Ações nacionais"),
    ACOES_INTERNACIONAIS("Ações internacionais"),
    FUNDOS_IMOBILIARIOS("Fundos imobiliarios"),
    REITS("Reits");

    private final String description;

    CategoryQuestionEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
