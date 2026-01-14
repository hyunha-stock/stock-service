package com.hyunha.stock.stock.infra.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunha.stock.stock.domain.port.out.FluctuationCacheReader;
import com.hyunha.stock.stock.infra.redis.dto.FluctuationResponse;
import com.hyunha.stock.stock.infra.redis.enums.RedisKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RedisFluctuationCacheReader implements FluctuationCacheReader {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<FluctuationResponse.Output> getTopGainers() {
        return getTopGainers(Integer.MAX_VALUE);
    }

    @Override
    public List<FluctuationResponse.Output> getTopGainers(int limit) {
        String key = RedisKey.TOP_GAINERS.getKey();
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json == null) return List.of();

        try {
            FluctuationResponse fluctuationResponse = objectMapper.readValue(json, FluctuationResponse.class);
            if (CollectionUtils.isEmpty(fluctuationResponse.getOutput())) {
                return List.of();
            }

            if (limit > fluctuationResponse.getOutput().size()) {
                return fluctuationResponse.getOutput();
            }

            return fluctuationResponse.getOutput().stream().limit(limit).toList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize redis cache. key=" + key, e);
        }
    }


    @Override
    public List<FluctuationResponse.Output> getTopLosers() {
        return getTopLosers(Integer.MAX_VALUE);
    }

    @Override
    public List<FluctuationResponse.Output> getTopLosers(int limit) {
        String key = RedisKey.TOP_LOSERS.getKey();
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json == null) return List.of();

        try {
            FluctuationResponse fluctuationResponse = objectMapper.readValue(json, FluctuationResponse.class);
            if (CollectionUtils.isEmpty(fluctuationResponse.getOutput())) {
                return List.of();
            }

            if (limit > fluctuationResponse.getOutput().size()) {
                return fluctuationResponse.getOutput();
            }

            return fluctuationResponse.getOutput().stream().limit(limit).toList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize redis cache. key=" + key, e);
        }
    }
}
