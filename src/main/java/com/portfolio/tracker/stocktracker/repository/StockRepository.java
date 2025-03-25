package com.portfolio.tracker.stocktracker.repository;

import com.portfolio.tracker.stocktracker.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
