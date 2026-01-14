package com.hyunha.stock.stock.api.dto;

import com.hyunha.stock.stock.domain.StockSearch;
import com.hyunha.stock.stock.infra.redis.dto.StockSearchItem;

import java.util.List;
import java.util.Objects;

public record GetSearchDefaultResponse(
        List<Stock> marketCapDesc,
        List<Stock> gainersDesc,
        List<Stock> losersAsc
) {

    public static GetSearchDefaultResponse defaultResponse(List<StockSearchItem> stockSearchItems) {
        List<StockSearchItem> topByMarketCap = stockSearchItems.stream().filter(ssi -> Objects.equals(ssi.type(), "ALL")).toList();
        List<StockSearchItem> topGainers = stockSearchItems.stream().filter(ssi -> Objects.equals(ssi.type(), "GAINER")).toList();
        List<StockSearchItem> topLosers = stockSearchItems.stream().filter(ssi -> Objects.equals(ssi.type(), "LOSER")).toList();

        return new GetSearchDefaultResponse(
                topByMarketCap.stream().map(GetSearchDefaultResponse.Stock::new).toList(),
                topGainers.stream().map(GetSearchDefaultResponse.Stock::new).toList(),
                topLosers.stream().map(GetSearchDefaultResponse.Stock::new).toList()
        );
    }

    public record Stock(String symbol, String name, String sector, String market, double changeRateFromPrevDay) {
        public Stock(StockSearchItem search) {
            this(search.symbol(), search.name(), search.sector(), search.market(), search.changeRateFromPrevDay());
        }

    }
}
