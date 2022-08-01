package com.multandras.currencyexchange.service;

import com.multandras.currencyexchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CurrencyService {
    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<String> findAllSymbols() {
        return currencyRepository.findAllSymbols();
    }

    public Optional<BigDecimal> findRateBySymbol(String symbol) {
        return currencyRepository.findRateBySymbol(symbol);
    }

    public ResponseEntity<?> convertCurrency(String from, String to, BigDecimal amount) {
        BigDecimal rateToHUF;
        BigDecimal rateFinal;
        try {
            rateToHUF = findRateBySymbol(from).orElseThrow();
            rateFinal = findRateBySymbol(to).orElseThrow();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(amount.multiply(rateToHUF).divide(rateFinal, RoundingMode.HALF_EVEN));
    }
}
