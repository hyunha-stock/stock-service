package com.hyunha.stock.stock.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hyunha.stock.stock.api.dto.GetSearchDefaultResponse;
import com.hyunha.stock.stock.api.dto.GetSearchResponse;
import com.hyunha.stock.stock.application.StockSearchQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/stocks/v1/search")
@RestController
public class StockSearchController {

    private final StockSearchQueryService stockSearchQueryService;

    @GetMapping("/defaults")
    public GetSearchDefaultResponse defaultResponse() throws JsonProcessingException {
        return stockSearchQueryService.defaultResponse();
    }

    @GetMapping
    public List<GetSearchResponse> search(String query) {
        return stockSearchQueryService.search(query);
    }
}
