package com.project.reactdashboard.infrastructure.configuration;

import com.project.reactdashboard.domain.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {"com.project.reactdashboard.domain.stock"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {UseCase.class})})
public class StockMarketConfiguration {
}
