package com.hyunha.stock.stock.api.dto;

import com.hyunha.stock.stock.infra.elasticsearch.document.NewsDocument;
import com.hyunha.stock.stock.infra.jpa.entity.Stock;
import org.springframework.util.CollectionUtils;

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
                CollectionUtils.isEmpty(newsDocument.getRelatedStockCodes()) ? List.of() : newsDocument.getRelatedStockCodes().stream()
                        .filter(relatedTicker -> {
                            String stockCode = relatedTicker.split("\\.")[0];
                            Stock stock = stockByStockCode.get(stockCode);
                            return stock != null;
                        })
                        .map(relatedTicker -> {
                            String stockCode = relatedTicker.split("\\.")[0];
                            Stock stock = stockByStockCode.get(stockCode);
                            return new RelatedStock(stockCode, stock.getNameKo());
                        }).toList()


        );
    }
}
