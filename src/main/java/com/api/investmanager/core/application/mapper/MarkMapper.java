package com.api.investmanager.core.application.mapper;


import com.api.investmanager.adapters.input.controller.mark.dto.MarkResponseDTO;
import com.api.investmanager.core.domain.model.Mark;
import com.api.investmanager.infra.database.entity.MarkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarkMapper {
    MarkMapper INSTANCE = Mappers.getMapper(MarkMapper.class);

    Mark markEntityToMarkModel(MarkEntity markEntity);

    List<MarkResponseDTO> markModelToMarkResponseDTO(List<Mark> marks);
}
