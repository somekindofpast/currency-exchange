package com.multandras.currencyexchange.controller;

import com.multandras.currencyexchange.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/symbols")
    public ResponseEntity<?> findAllSymbols() {
        return ResponseEntity.ok().body(currencyService.findAllSymbols());
    }

    @GetMapping("/rate/{symbol}")
    public ResponseEntity<?> findRateBySymbol(@PathVariable("symbol") String symbol) {
        Optional<BigDecimal> rate = currencyService.findRateBySymbol(symbol);
        if(rate.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(rate);
        }
    }

    @GetMapping("/convert")
    public ResponseEntity<?> convertCurrency(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("amount") BigDecimal amount) {
        return currencyService.convertCurrency(from, to, amount);
    }
}
