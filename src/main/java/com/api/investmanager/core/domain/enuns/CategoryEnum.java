package com.api.investmanager.core.domain.enuns;

public enum CategoryEnum {
    ACOES_NACIONAIS("Ações nacionais"),
    ACOES_INTERNACIONAIS("Ações internacionais"),
    FUNDOS_IMOBILIARIOS("Fundos imobiliarios"),
    REITS("Reits"),
    RENDA_FIXA("Renda fixa"),
    CRIPTOMOEDAS("Criptomoedas");

    private final String description;

    CategoryEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
