package com.hyunha.stock.stock.application;

import com.hyunha.stock.stock.api.dto.GetThemeResponse;
import com.hyunha.stock.stock.domain.port.out.StockCacheReader;
import com.hyunha.stock.stock.domain.port.out.StockCacheWriter;
import com.hyunha.stock.stock.infra.jpa.dto.ThemeResponse;
import com.hyunha.stock.stock.domain.port.out.ThemeQueryPort;
import com.hyunha.stock.stock.infra.redis.RedisKeyFactory;
import com.hyunha.stock.stock.infra.redis.dto.DomesticStockCurrentPriceResponse;
import com.hyunha.stock.stock.infra.redis.enums.RedisKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ThemeQueryService {

    private final ThemeQueryPort themeQueryPort;
    private final StockCacheReader stockCacheReader;

    public List<GetThemeResponse> getThemes() {
        List<ThemeResponse> themeResponses = themeQueryPort.getThemes();

        List<String> redisKeys = themeResponses.stream()
                .flatMap(themeResponse -> themeResponse.stockCodes().stream())
                .map(stockCode -> RedisKey.STOCK_INFO.getKey() + ":" + stockCode)
                .toList();

        List<DomesticStockCurrentPriceResponse> domesticStockCurrentPrices = stockCacheReader.getDomesticStockCurrentPrices(redisKeys);

        return themeResponses.stream()
                .map(themeResponse -> new GetThemeResponse(
                        themeResponse.themeCode(),
                        themeResponse.themeName(),
                        themeResponse.stockCount(),
                        domesticStockCurrentPrices.stream()
                                .filter(dscp -> themeResponse.stockCodes().contains(dscp.getOutput().getStockShortCode()))
                                .mapToDouble(dscp -> Double.parseDouble(dscp.getOutput().getChangeRateFromPreviousDay()))
                                .average().orElse(0)
                ))
                .toList();
    }
}
