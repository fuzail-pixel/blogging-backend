package com.inter.koffee.controller;

import com.inter.koffee.service.SummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @PostMapping
    public ResponseEntity<String> summarize(@RequestBody String text) {
        String summary = summaryService.summarizeText(text);
        return ResponseEntity.ok(summary);
    }
}
