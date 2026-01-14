package com.hyunha.stock.stock.api;

import com.hyunha.stock.stock.api.dto.GetRealTimeNewsResponse;
import com.hyunha.stock.stock.application.RealTimeNewsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/stocks/v1/news")
@RestController
public class RealTimeNewsController {

    private final RealTimeNewsQueryService realTimeNewsQueryService;

    @GetMapping
    public List<GetRealTimeNewsResponse> getRealTimeNews() {
        return realTimeNewsQueryService.getRealTimeNews();
    }
}
