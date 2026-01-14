package com.hyunha.stock.stock.domain.port.out;

import com.hyunha.stock.stock.infra.redis.dto.SymbolFeature;

import java.util.List;
import java.util.Optional;

public interface TradeRankingCacheReader {
    Optional<List<SymbolFeature>> getRanking();
}
