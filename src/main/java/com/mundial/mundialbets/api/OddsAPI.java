package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.OddsEntity;
import com.mundial.mundialbets.Models.OddsModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/odds")
public interface OddsAPI {
    @PostMapping
    ResponseEntity<OddsModel> addOdds(@RequestBody OddsModel oddsModel) throws Exception;

    @GetMapping
    ResponseEntity<OddsModel> getOdds(@RequestBody OddsModel oddsModel);
}
