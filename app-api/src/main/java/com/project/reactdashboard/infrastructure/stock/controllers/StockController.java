package com.project.reactdashboard.infrastructure.stock.controllers;

import com.project.reactdashboard.application.stock.StockApi;
import com.project.reactdashboard.application.stock.mapper.StockApplicationMapper;
import com.project.reactdashboard.application.stock.model.StockApplication;
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
@RequestMapping("/api/stocks")
public class StockController {

    private final StockApplicationMapper mapper;

    private final StockApi service;

    public StockController(StockApplicationMapper mapper, StockApi service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/latest")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StockDto>> findAllLatest() {
        List<StockApplication> stockApplications = service.findAllLatest();
        List<StockDto> dtos = mapper.toListDto(stockApplications);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StockDto>> findBySymbol(@PathVariable("symbol") String symbol) {
        List<StockApplication> stockApplications = service.findBySymbol(symbol);
        List<StockDto> dtos = mapper.toListDto(stockApplications);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{symbol}/last-working-day")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StockDto> findLastWorkingDayBySymbol(@PathVariable("symbol") String symbol) {
        StockApplication stockApplication = service.findLastWorkingDayBySymbol(symbol);
        StockDto dto = mapper.toDto(stockApplication);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> createAll(@RequestBody final List<StockDto> stockDtos) {
        List<StockApplication> stockApplications = mapper.toListApplication(stockDtos);
        service.createAll(stockApplications);
        return ResponseEntity.ok(1);
    }

}
