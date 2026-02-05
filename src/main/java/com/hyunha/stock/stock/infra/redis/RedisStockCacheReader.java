package com.hyunha.stock.stock.infra.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunha.stock.kis.infra.dto.DomesticStockPriceResponse;
import com.hyunha.stock.stock.api.dto.GetInvestmentOpinionResponse;
import com.hyunha.stock.stock.domain.port.out.StockCacheReader;
import com.hyunha.stock.stock.infra.redis.dto.DomesticStockCurrentPriceResponse;
import com.hyunha.stock.stock.infra.redis.dto.VolumeRankResponse;
import com.hyunha.stock.stock.infra.redis.enums.RedisKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RedisStockCacheReader implements StockCacheReader {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public Optional<List<GetInvestmentOpinionResponse>> getInvestmentOpinion(String symbol) {
        return Optional.empty();
    }

    @Override
    public List<DomesticStockCurrentPriceResponse> getDomesticStockCurrentPrices(List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) return List.of();
        List<String> jsonList = stringRedisTemplate.opsForValue().multiGet(keys);
        if (CollectionUtils.isEmpty(jsonList)) return List.of();

        return jsonList.stream()
                .filter(Objects::nonNull)
                .map(json -> {
                    try {
                        return objectMapper.readValue(json, DomesticStockCurrentPriceResponse.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    @Override
    public VolumeRankResponse getVolumeRanking() throws JsonProcessingException {
        String json = stringRedisTemplate.opsForValue().get(RedisKey.VOLUME_RANK.getKey());
        return objectMapper.readValue(json, VolumeRankResponse.class);
    }
}
