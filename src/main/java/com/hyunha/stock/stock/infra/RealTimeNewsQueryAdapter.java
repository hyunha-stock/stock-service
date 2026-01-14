package com.hyunha.stock.stock.infra;

import com.hyunha.stock.stock.api.dto.GetRealTimeNewsResponse;
import com.hyunha.stock.stock.domain.port.out.RealTimeNewsQueryPort;
import com.hyunha.stock.stock.infra.elasticsearch.document.NewsDocument;
import com.hyunha.stock.stock.infra.elasticsearch.repo.NewsEsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class RealTimeNewsQueryAdapter implements RealTimeNewsQueryPort {

    private final NewsEsRepository newsEsRepository;

    public List<GetRealTimeNewsResponse> getRealTimeNews() {
        List<NewsDocument> newsDocuments = newsEsRepository.findByOrderByCrawledAtDesc(Pageable.ofSize(100));
        log.info("newsDocuments size: {}", newsDocuments.size());
        return newsDocuments.stream()
                .map(GetRealTimeNewsResponse::fromDocument)
                .toList();
    }


}
