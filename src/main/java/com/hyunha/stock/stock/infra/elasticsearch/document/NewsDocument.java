package com.hyunha.stock.stock.infra.elasticsearch.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "momenta-news") // 인덱스명 맞게 변경
public class NewsDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ko_nori", termVector = TermVector.with_positions_offsets)
    private String body;

    @Field(type = FieldType.Date, format = DateFormat.date_time, name = "crawled_at")
    private String crawledAt;

    @Field(type = FieldType.Text, analyzer = "ko_nori")
    private String description;

    @Field(type = FieldType.Keyword)
    private String domain;

    @Field(type = FieldType.Date, format = DateFormat.date_time, name = "published_at")
    private String publishedAt;

    @Field(type = FieldType.Keyword)
    private String query;

    @Field(type = FieldType.Keyword)
    private String source;

    @Field(type = FieldType.Keyword)
    private String symbol;

    @MultiField(
            mainField = @Field(
                    type = FieldType.Text,
                    analyzer = "ko_nori",
                    termVector = TermVector.with_positions_offsets
            ),
            otherFields = {
                    @InnerField(suffix = "raw", type = FieldType.Keyword, ignoreAbove = 512)
            }
    )
    private String title;

    @Field(type = FieldType.Keyword)
    private String url;
}
