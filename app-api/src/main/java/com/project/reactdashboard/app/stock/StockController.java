package com.project.reactdashboard.app.stock;

import com.project.reactdashboard.domain.stock.StockApi;
import com.project.reactdashboard.domain.stock.mapper.StockDomainMapper;
import com.project.reactdashboard.domain.stock.model.StockDomain;
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

    private final StockDomainMapper mapper;

    private final StockApi service;

    public StockController(StockDomainMapper mapper, StockApi service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/latest")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StockDto>> findAllLatest() {
        List<StockDomain> stockDomains = service.findAllLatest();
        List<StockDto> dtos = mapper.toListDto(stockDomains);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StockDto>> findBySymbol(@PathVariable("symbol") String symbol) {
        List<StockDomain> stockDomains = service.findBySymbol(symbol);
        List<StockDto> dtos = mapper.toListDto(stockDomains);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{symbol}/last-working-day")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StockDto> findLastWorkingDayBySymbol(@PathVariable("symbol") String symbol) {
        StockDomain stockDomain = service.findLastWorkingDayBySymbol(symbol);
        StockDto dto = mapper.toDto(stockDomain);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> createAll(@RequestBody final List<StockDto> stockDtos) {
        List<StockDomain> stockDomains = mapper.toListDomain(stockDtos);
        service.createAll(stockDomains);
        return ResponseEntity.ok(1);
    }

}
