package com.project.reactdashboard.app.stock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.reactdashboard.app.stock.StockController;
import com.project.reactdashboard.domain.stock.StockMapper;
import com.project.reactdashboard.domain.stock.StockService;
import com.project.reactdashboard.domain.stock.model.Stock;
import com.project.reactdashboard.app.stock.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDto;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StockControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StockMapper mapper;

    @Mock
    private StockService service;

    @BeforeEach
    void setup() {
        openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new StockController(mapper, service))
                .build();
    }

    @Test
    void should_create_a_list_of_stock() throws Exception {
        List<StockDto> dtos = randomList(i -> randomStockDto());
        List<Stock> stocks = randomList(i -> randomStock());

        when(mapper.toList(dtos)).thenReturn(stocks);

        ResultActions resultActions = mockMvc.perform(
                post("/api/stocks/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonModel(stocks)));

        resultActions.andExpect(status().isOk());
    }

    @Test
    void should_return_a_stock() throws Exception {
        Stock stock = randomStock();
        StockDto dto = randomStockDto();

        when(service.findLastWorkingDayBySymbol(stock.getSymbol())).thenReturn(stock);
        when(mapper.toDto(stock)).thenReturn(dto);

        ResultActions resultActions = mockMvc.perform(
                get("/api/stocks/{symbol}/last-working-day", stock.getSymbol())
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(dto.getId()));
    }

    @Test
    void should_return_list_of_stock_by_symbol() throws Exception {
        Stock stock = randomStock();
        List<Stock> stocks = randomList(i -> stock);
        StockDto dto = randomStockDto();
        List<StockDto> dtos = randomList(i -> dto);

        when(service.findBySymbol(stock.getSymbol())).thenReturn(stocks);
        when(mapper.toListDto(stocks)).thenReturn(dtos);

        ResultActions resultActions = mockMvc.perform(
                get("/api/stocks/{symbol}", stock.getSymbol())
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(dtos.get(0).getId()));
    }

    @Test
    void should_return_list_of_latest_stock() throws Exception {
        Stock stock = randomStock();
        List<Stock> stocks = randomList(i -> stock);
        StockDto dto = randomStockDto();
        List<StockDto> dtos = randomList(i -> dto);

        when(service.findAllLatest()).thenReturn(stocks);
        when(mapper.toListDto(stocks)).thenReturn(dtos);

        ResultActions resultActions = mockMvc.perform(
                get("/api/stocks/latest")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(dtos.get(0).getId()));
    }

    private String createJsonModel(Object value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(value);
    }
}
