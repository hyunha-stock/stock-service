package com.hyunha.stock.stock.infra.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(
    name = "stock_master",
    indexes = {
        @Index(name = "idx_stock_master_sector", columnList = "market, sector_l, sector_m, sector_s"),
        @Index(name = "idx_stock_master_status", columnList = "market, status"),
        @Index(name = "idx_stock_master_symbol", columnList = "symbol")
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {

    @EmbeddedId
    private StockMasterId id;

    @Column(name = "std_code")
    private String stdCode;

    @Column(name = "name_ko")
    private String nameKo;

    @Column(name = "sector_l")
    private String sectorL;

    @Column(name = "sector_m")
    private String sectorM;

    @Column(name = "sector_s")
    private String sectorS;

    @Column(name = "market_cap")
    private Long marketCap;

    @Column(name = "listed_shares")
    private Long listedShares;

    @Column(name = "price_base")
    private Long priceBase;

    @Column(name = "is_trading_halt", nullable = false)
    private boolean tradingHalt = false;

    @Column(name = "is_liquidation", nullable = false)
    private boolean liquidation = false;

    @Column(name = "is_managed", nullable = false)
    private boolean managed = false;

    @Column(name = "is_warning", nullable = false)
    private boolean warning = false;

    @Column(name = "is_watch", nullable = false)
    private boolean watch = false;

    @Column(name = "is_overheat", nullable = false)
    private boolean overheat = false;

    @Column(name = "status", nullable = false)
    private String status = "ACTIVE";

    @Column(name = "missing_since")
    private LocalDate missingSince;

    @Column(name = "candidate_since")
    private LocalDate candidateSince;

    @Column(name = "delisted_at")
    private LocalDate delistedAt;

    @Column(name = "as_of_date", nullable = false)
    private LocalDate asOfDate;

    @Column(name = "row_hash", nullable = false)
    private String rowHash;

    // timestamp with time zone -> Instant 권장
    @CreationTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamptz")
    private Instant updatedAt;
}
