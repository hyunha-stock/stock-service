package com.hyunha.stock.stock.infra.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunha.stock.stock.domain.StockSearch;
import com.hyunha.stock.stock.domain.port.out.StockRankingCacheReader;
import com.hyunha.stock.stock.infra.redis.dto.StockSearchItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RedisStockRankingCacheReader implements StockRankingCacheReader {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<StockSearchItem> hitCache() throws JsonProcessingException {
        String json = stringRedisTemplate.opsForValue().get("search:defaults");
        return objectMapper.readValue(json, new TypeReference<>() {});
    }
}
