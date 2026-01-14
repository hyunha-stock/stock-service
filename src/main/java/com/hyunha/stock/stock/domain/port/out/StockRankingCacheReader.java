package com.hyunha.stock.stock.domain.port.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hyunha.stock.stock.infra.redis.dto.StockSearchItem;

import java.util.List;

public interface StockRankingCacheReader {
    List<StockSearchItem> hitCache() throws JsonProcessingException;
}
