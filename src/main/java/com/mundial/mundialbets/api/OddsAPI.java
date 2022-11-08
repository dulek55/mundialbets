package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.OddsEntity;
import com.mundial.mundialbets.Models.OddsModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/odds")
@CrossOrigin(origins = "http://localhost:3000/")
public interface OddsAPI {
    @PostMapping
    ResponseEntity<OddsModel> addOdds(@RequestBody OddsModel oddsModel) throws Exception;

    @GetMapping
    ResponseEntity<OddsModel> getOdds(@RequestBody OddsModel oddsModel);
}
