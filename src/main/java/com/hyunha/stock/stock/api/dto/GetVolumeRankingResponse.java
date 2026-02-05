package com.hyunha.stock.stock.api.dto;

public record GetVolumeRankingResponse(
        String stockCode,
        String stockName,
        String dataRank,
        String currentPrice,
        String prevDayDiff,
        String prevDayChangeRate
) {
}
