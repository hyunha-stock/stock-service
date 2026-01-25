package com.hyunha.stock.stock.infra.elasticsearch.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "momenta-news")
public class NewsDocument {

    @Id
    @Field(type = FieldType.Keyword) // mapping: id keyword
    private String id;

    @Field(type = FieldType.Text, analyzer = "ko_nori", termVector = TermVector.with_positions_offsets)
    private String body;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Nested, name = "companyCandidates")
    private List<CompanyCandidate> companyCandidates;

    @Field(type = FieldType.Date, format = DateFormat.date_time, name = "crawled_at")
    private String crawledAt;

    @Field(type = FieldType.Text, analyzer = "ko_nori")
    private String description;

    @Field(type = FieldType.Keyword)
    private String domain;

    @Field(type = FieldType.Date, format = DateFormat.date_time, name = "metadata_generated_at")
    private Instant metadataGeneratedAt;

    @Field(type = FieldType.Keyword, name = "metadata_model")
    private String metadataModel;

    @Field(type = FieldType.Keyword, name = "metadata_prompt_version")
    private String metadataPromptVersion;

    @Field(type = FieldType.Date, format = DateFormat.date_time, name = "openai_generated_at")
    private String openaiGeneratedAt;

    @MultiField(
            mainField = @Field(type = FieldType.Text, name = "openai_model"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword, ignoreAbove = 256)
            }
    )
    private String openaiModel;

    @Field(type = FieldType.Date, format = DateFormat.date_time, name = "published_at")
    private String publishedAt;

    @Field(type = FieldType.Keyword)
    private String query;

    @MultiField(
            mainField = @Field(type = FieldType.Text, name = "relatedStockCode"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword, ignoreAbove = 256)
            }
    )
    private List<String> relatedStockCode;

    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "ko_nori", name = "safeBrief"),
            otherFields = {
                    @InnerField(suffix = "raw", type = FieldType.Keyword, ignoreAbove = 256)
            }
    )
    private String safeBrief;

    @Field(type = FieldType.Keyword)
    private String sentiment;

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

    // -------- nested type: companyCandidates --------
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CompanyCandidate {

        @Field(type = FieldType.Keyword)
        private String countryHint;

        @MultiField(
                mainField = @Field(type = FieldType.Text, analyzer = "ko_nori"),
                otherFields = {
                        @InnerField(suffix = "raw", type = FieldType.Keyword, ignoreAbove = 256)
                }
        )
        private String name;
    }
}
