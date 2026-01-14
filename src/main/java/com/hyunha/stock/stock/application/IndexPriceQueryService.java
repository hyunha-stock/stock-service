package com.hyunha.stock.stock.application;

import com.hyunha.stock.stock.domain.port.out.IndexPriceCacheReader;
import com.hyunha.stock.stock.infra.redis.dto.IndexPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IndexPriceQueryService {

    private final IndexPriceCacheReader indexPriceCacheReader;

    public IndexPriceResponse.Output getIndexPrice() {
        return indexPriceCacheReader.getIndexPrice().orElseThrow();
    }
}
