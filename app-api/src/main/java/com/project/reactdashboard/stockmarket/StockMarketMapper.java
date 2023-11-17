package com.project.reactdashboard.stockmarket;

import com.project.reactdashboard.entities.Model;
import com.project.reactdashboard.entities.ModelDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockMarketMapper {

    public Model toEntity(ModelDto dto) {
        return new Model.ModelBuilder()
                .withId(dto.getId())
                .withValue(dto.getValue())
                .build();
    }

    public ModelDto toDto(Model entity) {
        return new ModelDto.ModelDtoBuilder()
                .withId(entity.getId())
                .withValue(entity.getValue())
                .build();
    }

    public List<ModelDto> toListDto(List<Model> models) {
        return models.stream()
                .map(this::toDto)
                .toList();
    }
}
