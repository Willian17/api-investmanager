package com.api.investmanager.adapters.input.controller.mark.dto;

import com.api.investmanager.core.domain.enuns.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarkResponseDTO {
    private String id;
    private CategoryEnum category;
    private Integer percentage;
}
