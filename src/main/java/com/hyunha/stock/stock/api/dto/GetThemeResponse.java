package com.hyunha.stock.stock.api.dto;

public record GetThemeResponse(
        String themeCode,
        String themeName,
        long stockCount,
        double averageChangeRate
) {
}
