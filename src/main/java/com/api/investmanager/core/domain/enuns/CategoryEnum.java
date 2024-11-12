package com.api.investmanager.core.domain.enuns;

public enum CategoryEnum {
    ACOES_NACIONAIS("AN"),
    ACOES_INTERNACIONAIS("AI"),
    FUNDOS_IMOBILIARIOS("FI"),
    REITS("RT"),
    RENDA_FIXA("RF"),
    CRIPTOMOEDA("CM");

    private final String code;

    CategoryEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
