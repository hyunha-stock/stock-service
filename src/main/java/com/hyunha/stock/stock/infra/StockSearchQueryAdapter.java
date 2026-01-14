package com.hyunha.stock.stock.infra;

import com.hyunha.stock.stock.api.dto.GetSearchResponse;
import com.hyunha.stock.stock.domain.port.out.StockSearchQueryPort;
import com.hyunha.stock.stock.infra.jpa.entity.Stock;
import com.hyunha.stock.stock.infra.jpa.projection.StockSearchDto;
import com.hyunha.stock.stock.infra.jpa.repo.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class StockSearchQueryAdapter implements StockSearchQueryPort {

    private final StockRepository stockRepository;

    @Override
    public List<GetSearchResponse> search(String query) {
        return stockRepository.searchKopisBySymbolOrNameKo(query).stream()
                .map(GetSearchResponse::fromEntity)
                .toList();
    }
}
