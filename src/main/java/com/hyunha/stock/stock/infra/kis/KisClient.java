package com.hyunha.stock.stock.infra.kis;

import com.hyunha.stock.kis.infra.dto.CandlesResponse;
import com.hyunha.stock.kis.infra.dto.DomesticStockPriceResponse;
import com.hyunha.stock.kis.infra.dto.InvestmentOpinionApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(name = "kis-gateway")
public interface KisClient {
    @GetMapping("/api/kis/v1/candles")
    CandlesResponse fetchCandles(
            @RequestParam String excd,
            @RequestParam String symbol,
            @RequestParam int nmin,
            @RequestParam int limit
    );

    @GetMapping("/api/kis/v1/investment-opinion")
    InvestmentOpinionApiResponse fetchInvestmentOpinion(
            @RequestParam String symbol,
            @RequestParam LocalDateTime now
    );

    @GetMapping("/api/kis/v1/domestic-stock-period-prices")
    DomesticStockPriceResponse fetchDomesticStockPeriodPrices(
            @RequestParam String symbol,
            @RequestParam String period,
            @RequestParam String from,
            @RequestParam String to);

}
