package com.hyunha.stock.stock.domain.port.out;

import aj.org.objectweb.asm.commons.InstructionAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hyunha.stock.kis.infra.dto.DomesticStockPriceResponse;
import com.hyunha.stock.stock.api.dto.GetInvestmentOpinionResponse;
import com.hyunha.stock.stock.infra.redis.dto.DomesticStockCurrentPriceResponse;
import com.hyunha.stock.stock.infra.redis.dto.VolumeRankResponse;

import java.util.List;
import java.util.Optional;

public interface StockCacheReader {
    Optional<List<GetInvestmentOpinionResponse>> getInvestmentOpinion(String symbol);

    List<DomesticStockCurrentPriceResponse> getDomesticStockCurrentPrices(List<String> keys);

    VolumeRankResponse getVolumeRanking() throws JsonProcessingException;
}
