package com.project.reactdashboard.infrastructure.stock.controllers.mapper;

import com.project.reactdashboard.domain.stock.model.StockModel;
import com.project.reactdashboard.infrastructure.stock.controllers.StockDto;
import com.project.reactdashboard.infrastructure.stock.entities.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDto;
import static com.project.reactdashboard.ObjectRandomizer.randomStockModel;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockMapperTest {

    private StockMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new StockMapper();
    }

    @Test
    void toEntity() {
        StockDto dto = randomStockDto();

        StockModel stockApplication = mapper.toModel(dto);

        assertEquals("2019-02-01T00:00Z", stockApplication.date().toString());
        assertEquals(dto.symbol(), stockApplication.symbol());
        assertEquals(dto.volume(), stockApplication.volume());
        assertEquals(dto.high(), stockApplication.high());
        assertEquals(dto.low(), stockApplication.low());
        assertEquals(dto.open(), stockApplication.open());
        assertEquals(dto.close(), stockApplication.close());
    }

    @Test
    void toModel() {
        Stock stock = randomStock();

        StockModel stockModel = mapper.toModel(stock);

        assertEquals(stock.getDate(), stockModel.date());
        assertEquals(stock.getSymbol(), stockModel.symbol());
        assertEquals(stock.getVolume(), stockModel.volume());
        assertEquals(stock.getHigh(), stockModel.high());
        assertEquals(stock.getLow(), stockModel.low());
        assertEquals(stock.getOpen(), stockModel.open());
        assertEquals(stock.getClose(), stockModel.close());
    }

    @Test
    void toDto() {
        StockModel stockModel = randomStockModel();

        StockDto dto = mapper.toDto(stockModel);

        assertEquals(dto.id(), stockModel.id());
        assertEquals(dto.date(), stockModel.date().toString());
        assertEquals(dto.symbol(), stockModel.symbol());
        assertEquals(dto.name(), stockModel.symbolValues().name());
        assertEquals(dto.volume(), stockModel.volume());
        assertEquals(dto.high(), stockModel.high());
        assertEquals(dto.low(), stockModel.low());
        assertEquals(dto.open(), stockModel.open());
        assertEquals(dto.close(), stockModel.close());
    }

    @Test
    void toListDto() {
        List<StockModel> stockModels = randomList(i -> randomStockModel());

        List<StockDto> dtos = mapper.toListDto(stockModels);

        assertEquals(stockModels.size(), dtos.size());
        assertEquals(stockModels.getFirst().id(), dtos.getFirst().id());
    }

    @Test
    void toList() {
        List<StockDto> dtos = randomList(i -> randomStockDto());

        List<StockModel> stockModels = mapper.toListDomain(dtos);

        assertEquals(dtos.size(), stockModels.size());
        assertEquals(dtos.getFirst().id(), stockModels.getFirst().id());
    }
}
