package com.project.reactdashboard.stockmarket;

import com.project.reactdashboard.entities.Model;
import com.project.reactdashboard.entities.ModelDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomInt;
import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockMarketMapperTest {

    private StockMarketMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new StockMarketMapper();
    }

    @Test
    void toEntity() {
        ModelDto dto = new ModelDto.ModelDtoBuilder()
                .withId(randomInt())
                .withValue(randomString())
                .build();

        Model model = mapper.toEntity(dto);

        assertEquals(dto.getId(), model.getId());
        assertEquals(dto.getValue(), model.getValue());
    }

    @Test
    void toDto() {
        Model model = new Model.ModelBuilder()
                .withId(randomInt())
                .withValue(randomString())
                .build();

        ModelDto dto = mapper.toDto(model);

        assertEquals(model.getId(), dto.getId());
        assertEquals(model.getValue(), dto.getValue());
    }

    @Test
    void toListDto() {
        Model model = new Model.ModelBuilder()
                .withId(randomInt())
                .withValue(randomString())
                .build();
        List<Model> models = randomList(i -> model);

        List<ModelDto> dtos = mapper.toListDto(models);

        assertEquals(models.size(), dtos.size());
    }
}
