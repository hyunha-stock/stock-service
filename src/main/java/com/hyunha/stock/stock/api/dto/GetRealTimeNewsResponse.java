package com.hyunha.stock.stock.api.dto;

import com.hyunha.stock.stock.infra.elasticsearch.document.NewsDocument;
import com.hyunha.stock.stock.infra.jpa.entity.Stock;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public record GetRealTimeNewsResponse(
        String newsId,
        String title,
        String description,
        String source,
        String url,
        String crawledAt,

        String safeBrief,
        String sentiment,
        String category,
        List<RelatedStock> relatedStock

) {
    public record RelatedStock(String stockCode, String name) {}

    public static GetRealTimeNewsResponse fromDocument(NewsDocument newsDocument, Map<String, Stock> stockByStockCode) {
        return new GetRealTimeNewsResponse(
                newsDocument.getId(),
                newsDocument.getTitle(),
                newsDocument.getDescription(),
                newsDocument.getSource(),
                newsDocument.getUrl(),
                newsDocument.getCrawledAt(),

                newsDocument.getSafeBrief(),
                newsDocument.getSentiment(),
                newsDocument.getCategory(),
                CollectionUtils.isEmpty(newsDocument.getRelatedTickers()) ? List.of() : newsDocument.getRelatedTickers().stream()
                        .map(relatedTicker -> {
                            Stock stock = stockByStockCode.get(relatedTicker);
                            if (stock != null) {
                                return new RelatedStock(relatedTicker, stock.getNameKo());
                            } else {
                                return new RelatedStock(relatedTicker, "");
                            }
                        }).toList()


        );
    }
}
