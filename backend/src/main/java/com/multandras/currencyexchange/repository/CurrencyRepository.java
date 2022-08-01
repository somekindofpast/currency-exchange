package com.multandras.currencyexchange.repository;

import com.multandras.currencyexchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Query("SELECT c.symbol FROM Currency c")
    List<String> findAllSymbols();

    @Query("SELECT c.rate FROM Currency c WHERE c.symbol =:symbol")
    Optional<BigDecimal> findRateBySymbol(String symbol);
}
