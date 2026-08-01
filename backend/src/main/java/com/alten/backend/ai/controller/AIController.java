package com.alten.backend.ai.controller;

import com.alten.backend.ai.dto.AIdto;
import com.alten.backend.ai.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/tickets/analyze")
@CrossOrigin(origins = "*")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping
    public ResponseEntity<AIdto> analyzeTicket(@RequestBody Map<String, String> requestBody) {
        String ticketDescription = requestBody.get("description");
        AIdto analysisResult = aiService.analyzeTicket(ticketDescription);
        return ResponseEntity.ok(analysisResult);
    }

}
