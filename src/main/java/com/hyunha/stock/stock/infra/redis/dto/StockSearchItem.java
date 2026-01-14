package com.hyunha.stock.stock.infra.redis.dto;

public record StockSearchItem(
        String symbol,
        String name,
        String sector,                 // null 가능
        String market,                 // "KOSPI" 등
        long currentPrice,             // 1,878,000 같은 값 안전하게 받으려면 long 추천
        double changeRateFromPrevDay,  // -1.59, 0.14 등
        String type                    // "ALL" | "GAINER" | "LOSER"
) {}