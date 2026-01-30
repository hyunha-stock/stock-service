package com.hyunha.stock.stock.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/stocks/v1/themes")
@RestController
public class ThemeController {

    @GetMapping
    public void getThemes() {

    }
}
