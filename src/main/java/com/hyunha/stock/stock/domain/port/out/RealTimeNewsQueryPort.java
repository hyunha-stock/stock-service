package com.hyunha.stock.stock.domain.port.out;

import com.hyunha.stock.stock.api.dto.GetRealTimeNewsResponse;

import java.util.List;

public interface RealTimeNewsQueryPort {

    List<GetRealTimeNewsResponse> getRealTimeNews();
}
