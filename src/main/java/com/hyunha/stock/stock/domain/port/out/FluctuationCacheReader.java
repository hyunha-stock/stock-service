package com.hyunha.stock.stock.domain.port.out;

import com.hyunha.stock.stock.infra.redis.dto.FluctuationResponse;

import java.util.List;
import java.util.Optional;

public interface FluctuationCacheReader {

    List<FluctuationResponse.Output> getTopGainers();
    List<FluctuationResponse.Output> getTopGainers(int limit);
    List<FluctuationResponse.Output> getTopLosers();
    List<FluctuationResponse.Output> getTopLosers(int limit);
}
