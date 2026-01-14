package com.hyunha.stock.stock.application;

import com.hyunha.stock.stock.domain.port.out.FluctuationCacheReader;
import com.hyunha.stock.stock.infra.redis.dto.FluctuationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FluctuationQueryService {

    private final FluctuationCacheReader fluctuationCacheReader;

    public List<FluctuationResponse.Output> getTopGainers() {
        return fluctuationCacheReader.getTopGainers();
    }

    public List<FluctuationResponse.Output> getTopLosers() {
        return fluctuationCacheReader.getTopLosers();
    }
}
