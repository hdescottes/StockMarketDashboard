package com.project.reactdashboard.stock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.reactdashboard.entities.Stock;
import com.project.reactdashboard.entities.StockDto;
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
    void should_create_a_stock() throws Exception {
        StockDto dto = randomStockDto();
        Stock stock = randomStock();

        when(mapper.toEntity(dto)).thenReturn(stock);

        ResultActions resultActions = mockMvc.perform(
                post("/api/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonModel(stock)));

        resultActions.andExpect(status().isOk());
    }

    @Test
    void should_return_a_stock() throws Exception {
        Stock stock = randomStock();
        StockDto dto = randomStockDto();

        when(service.findById(stock.getId())).thenReturn(stock);
        when(mapper.toDto(stock)).thenReturn(dto);

        ResultActions resultActions = mockMvc.perform(
                get("/api/stock/{id}", stock.getId())
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(dto.getId()));
    }

    @Test
    void should_return_list_of_stock() throws Exception {
        Stock stock = randomStock();
        List<Stock> stocks = randomList(i -> stock);
        StockDto dto = randomStockDto();
        List<StockDto> dtos = randomList(i -> dto);

        when(service.findAll()).thenReturn(stocks);
        when(mapper.toListDto(stocks)).thenReturn(dtos);

        ResultActions resultActions = mockMvc.perform(
                get("/api/stock")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(dtos.get(0).getId()));
    }

    private String createJsonModel(Object value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(value);
    }
}
