package com.hyunha.stock.stock.domain.port.out;

import com.hyunha.stock.stock.infra.jpa.dto.ThemeResponse;

import java.util.List;

public interface ThemeQueryPort {
    List<ThemeResponse> getThemes();

}
