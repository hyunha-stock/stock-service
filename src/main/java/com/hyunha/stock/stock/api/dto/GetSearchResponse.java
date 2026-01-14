package com.hyunha.stock.stock.api.dto;

import com.hyunha.stock.stock.infra.jpa.projection.StockSearchDto;

public record GetSearchResponse (
        String symbol, String name, String market
) {
    public static GetSearchResponse fromEntity(StockSearchDto stockSearchDto) {
        return new GetSearchResponse(stockSearchDto.getSymbol(), stockSearchDto.getNameKo(), stockSearchDto.getMarket());
    }
}
