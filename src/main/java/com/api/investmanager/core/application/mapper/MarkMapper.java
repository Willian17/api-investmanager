package com.api.investmanager.core.application.mapper;


import com.api.investmanager.adapters.input.controller.mark.dto.MarkResponseDTO;
import com.api.investmanager.core.domain.model.Mark;
import com.api.investmanager.infra.database.entity.MarkEntity;

import java.util.List;

public class MarkMapper {

    public static Mark markEntityToMarkModel(MarkEntity markEntity) {
        return new Mark(String.valueOf(markEntity.getId()), markEntity.getPercentage(), markEntity.getCategory());
    }

    public static List<MarkResponseDTO> markModelToMarkResponseDTO(List<Mark> marks) {
        return marks.stream()
                .map(mark ->
                        new MarkResponseDTO(mark.getId(), mark.getCategory(), mark.getPercentage()))
                .toList();
    }
}
