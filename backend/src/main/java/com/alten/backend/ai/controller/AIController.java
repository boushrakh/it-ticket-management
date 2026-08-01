package com.alten.backend.ai.controller;

import com.alten.backend.ai.dto.AIdto;
import com.alten.backend.ai.service.AIService;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/tickets/analyze")
@CrossOrigin(origins = "*")
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
>>>>>>> feature/frontend-coreui
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

<<<<<<< HEAD
    @PostMapping
    public ResponseEntity<AIdto> analyzeTicket(@RequestBody Map<String, String> requestBody) {
        String ticketDescription = requestBody.get("description");
        AIdto analysisResult = aiService.analyzeTicket(ticketDescription);
        return ResponseEntity.ok(analysisResult);
    }

=======
    @PostMapping("/analyze")
    public ResponseEntity<AIdto> analyze(@RequestBody Map<String, String> payload) {
        String description = payload.getOrDefault("description", payload.getOrDefault("title", ""));
        return ResponseEntity.ok(aiService.analyzeTicket(description));
    }
>>>>>>> feature/frontend-coreui
}
