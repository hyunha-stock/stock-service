package com.hyunha.stock.stock.infra.jpa.repo;

import com.hyunha.stock.stock.infra.jpa.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, String> {
}
