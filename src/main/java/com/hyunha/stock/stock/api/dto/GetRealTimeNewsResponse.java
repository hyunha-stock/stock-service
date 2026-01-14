package com.hyunha.stock.stock.api.dto;

import com.hyunha.stock.stock.infra.elasticsearch.document.NewsDocument;

import java.time.Instant;
import java.time.LocalDateTime;

public record GetRealTimeNewsResponse(
        String newsId,
        String title,
        String description,
        String source,
        String url,
        String crawledAt

) {
    public static GetRealTimeNewsResponse fromDocument(NewsDocument newsDocument) {
        return new GetRealTimeNewsResponse(
                newsDocument.getId(),
                newsDocument.getTitle(),
                newsDocument.getDescription(),
                newsDocument.getSource(),
                newsDocument.getUrl(),
                newsDocument.getCrawledAt()
        );
    }
}
