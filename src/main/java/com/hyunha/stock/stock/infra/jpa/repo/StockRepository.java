package com.hyunha.stock.stock.infra.jpa.repo;

import com.hyunha.stock.stock.infra.jpa.entity.Stock;
import com.hyunha.stock.stock.infra.jpa.entity.StockMasterId;
import com.hyunha.stock.stock.infra.jpa.projection.StockSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, StockMasterId> {

    @Query(
            value = """
                    select market, symbol, name_ko
                    from stock_master
                    where market = 'KOSPI' and
                          (symbol like '%' || :q || '%' or name_ko ilike '%' || :q || '%')
                    order by
                      greatest(
                        similarity(name_ko, :q),
                        case
                          when :q ~ '^[0-9]+$' then similarity(symbol, :q) * 2
                          else similarity(symbol, :q)
                        end
                      ) desc,
                      symbol
                    limit 10
                    """,
            nativeQuery = true
    )
    List<StockSearchDto> searchKopisBySymbolOrNameKo(@Param("q") String query);
}
