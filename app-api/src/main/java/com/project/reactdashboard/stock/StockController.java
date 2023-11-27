package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import com.project.reactdashboard.entities.StockDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockMapper mapper;

    private final StockService service;

    public StockController(StockMapper mapper, StockService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StockDto>> findAll() {
        List<Stock> stocks = service.findAll();
        List<StockDto> dtos = mapper.toListDto(stocks);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StockDto> findById(@PathVariable("id") String id) {
        Stock stock = service.findById(id);
        StockDto dto = new StockDto.StockDtoBuilder().build();
        if (stock != null) {
            dto = mapper.toDto(stock);
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> create(@RequestBody StockDto stockDto) {
        Stock stock = mapper.toEntity(stockDto);
        service.create(stock);
        return ResponseEntity.ok(1);
    }

}
