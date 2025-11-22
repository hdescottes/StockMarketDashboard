package com.project.reactdashboard.infrastructure.stock.controllers;

import com.project.reactdashboard.domain.stock.api.StockApi;
import com.project.reactdashboard.domain.stock.model.StockModel;
import com.project.reactdashboard.infrastructure.stock.controllers.mapper.StockMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDto;
import static com.project.reactdashboard.ObjectRandomizer.randomStockModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class StockControllerTest {

    private RestTestClient client;

    @Mock
    private StockMapper mapper;

    @Mock
    private StockApi service;

    @BeforeEach
    void setup() {
        openMocks(this);
        client = RestTestClient.bindToController(new StockController(mapper, service)).build();
    }

    @Test
    void should_create_a_list_of_stock() {
        List<StockDto> dtos = randomList(i -> randomStockDto());
        List<StockModel> stocks = randomList(i -> randomStockModel());

        when(mapper.toListDomain(dtos)).thenReturn(stocks);

        Integer result = client.post()
                .uri("/api/stocks/all")
                .body(dtos)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .returnResult()
                .getResponseBody();

        assertEquals(1, result);
    }

    @Test
    void should_return_a_stock() {
        StockModel stockApplication = randomStockModel();
        StockDto dto = randomStockDto();

        when(service.findLastWorkingDayBySymbol(stockApplication.symbol())).thenReturn(stockApplication);
        when(mapper.toDto(stockApplication)).thenReturn(dto);

        StockDto result = client.get()
                .uri("/api/stocks/{symbol}/last-working-day", stockApplication.symbol())
                .exchange()
                .expectStatus().isOk()
                .expectBody(StockDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(result);
        assertEquals(dto.id(), result.id());
    }

    @Test
    void should_return_list_of_stock_by_symbol() {
        StockModel stockApplication = randomStockModel();
        List<StockModel> stockApplications = randomList(i -> stockApplication);
        StockDto dto = randomStockDto();
        List<StockDto> dtos = randomList(i -> dto);

        when(service.findBySymbol(stockApplication.symbol())).thenReturn(stockApplications);
        when(mapper.toListDto(stockApplications)).thenReturn(dtos);

        List<StockDto> result = client.get()
                .uri("/api/stocks/{symbol}", stockApplication.symbol())
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<@NotNull List<StockDto>>(){})
                .returnResult()
                .getResponseBody();

        assertNotNull(result);
        assertEquals(dtos.size(), result.size());
        assertEquals(dto.id(), result.getFirst().id());
    }

    @Test
    void should_return_list_of_latest_stock() {
        StockModel stockApplication = randomStockModel();
        List<StockModel> stockApplications = randomList(i -> stockApplication);
        StockDto dto = randomStockDto();
        List<StockDto> dtos = randomList(i -> dto);

        when(service.findAllLatest()).thenReturn(stockApplications);
        when(mapper.toListDto(stockApplications)).thenReturn(dtos);

        List<StockDto> result = client.get()
                .uri("/api/stocks/latest")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<@NotNull List<StockDto>>(){})
                .returnResult()
                .getResponseBody();

        assertNotNull(result);
        assertEquals(dtos.size(), result.size());
        assertEquals(dto.id(), result.getFirst().id());
    }
}
