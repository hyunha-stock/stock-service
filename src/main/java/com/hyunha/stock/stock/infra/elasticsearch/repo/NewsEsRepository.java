package com.hyunha.stock.stock.infra.elasticsearch.repo;

import com.hyunha.stock.stock.infra.elasticsearch.document.NewsDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface NewsEsRepository extends ElasticsearchRepository<NewsDocument, String> {
    // recent 100 news
    List<NewsDocument> findByOrderByPublishedAt(Pageable pageable);

}
