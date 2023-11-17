package com.project.reactdashboard.stockmarket;

import com.project.reactdashboard.entities.Model;
import com.project.reactdashboard.entities.ModelDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock-market")
public class StockMarketController {

    private final StockMarketMapper mapper;

    private final StockMarketService service;

    public StockMarketController(StockMarketMapper mapper, StockMarketService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ModelDto>> findAll() {
        List<Model> models = service.findAll();
        List<ModelDto> dtos = mapper.toListDto(models);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> create(@RequestBody ModelDto modeldto) {
        Model model = mapper.toEntity(modeldto);
        service.create(model);
        return ResponseEntity.ok(1);
    }

}
