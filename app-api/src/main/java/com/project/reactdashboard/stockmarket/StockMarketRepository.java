package com.project.reactdashboard.stockmarket;

import com.project.reactdashboard.entities.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMarketRepository extends CrudRepository<Model, String> {

}
