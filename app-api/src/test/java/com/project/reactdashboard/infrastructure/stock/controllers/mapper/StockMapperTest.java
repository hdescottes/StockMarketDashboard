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

        assertEquals("2019-02-01T00:00Z", stockApplication.getDate().toString());
        assertEquals(dto.getSymbol(), stockApplication.getSymbol());
        assertEquals(dto.getVolume(), stockApplication.getVolume());
        assertEquals(dto.getHigh(), stockApplication.getHigh());
        assertEquals(dto.getLow(), stockApplication.getLow());
        assertEquals(dto.getOpen(), stockApplication.getOpen());
        assertEquals(dto.getClose(), stockApplication.getClose());
    }

    @Test
    void toModel() {
        Stock stock = randomStock();

        StockModel stockModel = mapper.toModel(stock);

        assertEquals(stock.getDate(), stockModel.getDate());
        assertEquals(stock.getSymbol(), stockModel.getSymbol());
        assertEquals(stock.getVolume(), stockModel.getVolume());
        assertEquals(stock.getHigh(), stockModel.getHigh());
        assertEquals(stock.getLow(), stockModel.getLow());
        assertEquals(stock.getOpen(), stockModel.getOpen());
        assertEquals(stock.getClose(), stockModel.getClose());
    }

    @Test
    void toDto() {
        StockModel stockModel = randomStockModel();

        StockDto dto = mapper.toDto(stockModel);

        assertEquals(dto.getId(), stockModel.getId());
        assertEquals(dto.getDate(), stockModel.getDate().toString());
        assertEquals(dto.getSymbol(), stockModel.getSymbol());
        assertEquals(dto.getName(), stockModel.getSymbolValues().getName());
        assertEquals(dto.getVolume(), stockModel.getVolume());
        assertEquals(dto.getHigh(), stockModel.getHigh());
        assertEquals(dto.getLow(), stockModel.getLow());
        assertEquals(dto.getOpen(), stockModel.getOpen());
        assertEquals(dto.getClose(), stockModel.getClose());
    }

    @Test
    void toListDto() {
        List<StockModel> stockModels = randomList(i -> randomStockModel());

        List<StockDto> dtos = mapper.toListDto(stockModels);

        assertEquals(stockModels.size(), dtos.size());
        assertEquals(stockModels.getFirst().getId(), dtos.getFirst().getId());
    }

    @Test
    void toList() {
        List<StockDto> dtos = randomList(i -> randomStockDto());

        List<StockModel> stockModels = mapper.toListDomain(dtos);

        assertEquals(dtos.size(), stockModels.size());
        assertEquals(dtos.getFirst().getId(), stockModels.getFirst().getId());
    }
}
