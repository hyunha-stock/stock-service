package com.hyunha.stock.stock.application;

import com.hyunha.stock.stock.api.dto.GetRealTimeNewsResponse;
import com.hyunha.stock.stock.domain.port.out.RealTimeNewsQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RealTimeNewsQueryService {

    private final RealTimeNewsQueryPort realTimeNewsQueryPort;

    public List<GetRealTimeNewsResponse> getRealTimeNews() {
        return realTimeNewsQueryPort.getRealTimeNews();
    }



}
