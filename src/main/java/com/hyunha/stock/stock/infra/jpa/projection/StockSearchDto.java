package com.hyunha.stock.stock.infra.jpa.projection;

public interface StockSearchDto {
    String getSymbol();
    String getNameKo();
    String getMarket();
}