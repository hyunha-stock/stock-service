package com.hyunha.stock.stock.domain.port.out;

import com.hyunha.stock.kis.infra.dto.DomesticStockPriceResponse;

import java.util.Optional;

public interface CandleCacheReader {
    Optional<DomesticStockPriceResponse> getCandles(String key);
}
