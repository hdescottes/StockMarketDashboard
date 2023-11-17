package com.project.reactdashboard.stockmarket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.reactdashboard.entities.Model;
import com.project.reactdashboard.entities.ModelDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomInt;
import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StockMarketControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StockMarketMapper mapper;

    @Mock
    private StockMarketService service;

    @BeforeEach
    void setup() {
        openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new StockMarketController(mapper, service))
                .build();
    }

    @Test
    void should_create_a_model() throws Exception {
        ModelDto dto = new ModelDto.ModelDtoBuilder()
                .withId(randomInt())
                .withValue(randomString())
                .build();
        Model model = new Model.ModelBuilder()
                .withId(randomInt())
                .withValue(randomString())
                .build();

        when(mapper.toEntity(dto)).thenReturn(model);

        ResultActions resultActions = mockMvc.perform(
                post("/api/stock-market")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonModel(model)));

        resultActions.andExpect(status().isOk());
    }

    @Test
    void should_return_list_of_model() throws Exception {
        Model model = new Model.ModelBuilder()
                .withId(randomInt())
                .withValue(randomString())
                .build();
        List<Model> models = randomList(i -> model);
        ModelDto dto = new ModelDto.ModelDtoBuilder()
                .withId(randomInt())
                .withValue(randomString())
                .build();
        List<ModelDto> dtos = randomList(i -> dto);

        when(service.findAll()).thenReturn(models);
        when(mapper.toListDto(models)).thenReturn(dtos);

        ResultActions resultActions = mockMvc.perform(
                get("/api/stock-market")
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
