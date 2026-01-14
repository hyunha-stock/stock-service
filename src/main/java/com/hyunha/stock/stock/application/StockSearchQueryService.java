package com.hyunha.stock.stock.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hyunha.stock.stock.api.dto.GetSearchDefaultResponse;
import com.hyunha.stock.stock.api.dto.GetSearchResponse;
import com.hyunha.stock.stock.domain.port.out.StockRankingCacheReader;
import com.hyunha.stock.stock.domain.port.out.StockSearchQueryPort;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockSearchQueryService {

    private final StockRankingCacheReader stockRankingCacheReader;
    private final StockSearchQueryPort stockSearchQueryPort;

    public GetSearchDefaultResponse defaultResponse() throws JsonProcessingException {
        return GetSearchDefaultResponse.defaultResponse(stockRankingCacheReader.hitCache());
    }

    public List<GetSearchResponse> search(String query) {
        if (StringUtils.isBlank(query)) return List.of();
        return stockSearchQueryPort.search(query);
    }
}
