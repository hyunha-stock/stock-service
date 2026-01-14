package com.hyunha.stock.stock.domain.port.out;

import com.hyunha.stock.stock.api.dto.GetSearchResponse;

import java.util.List;

public interface StockSearchQueryPort {
    List<GetSearchResponse> search(String query);
}
