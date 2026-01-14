package com.hyunha.stock.stock.domain.port.out;

import com.hyunha.stock.stock.infra.redis.dto.IndexPriceResponse;

import java.util.Optional;

public interface IndexPriceCacheReader {
    Optional<IndexPriceResponse.Output> getIndexPrice();
}
