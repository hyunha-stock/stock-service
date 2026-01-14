package com.hyunha.stock.stock.domain;

public record StockSearch(
        String symbol,
        String name,
        String sector,
        String market,
        double changeRateFromPrevDay
        ) {


}

