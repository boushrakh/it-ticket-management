package com.alten.backend.ai.service;

import com.alten.backend.ai.dto.AIdto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AIServiceTest {

    private final AIService aiService = new AIService();

    @Test
    void analyzeTicketShouldDetectHardwareAndHighPriority() {
        AIdto analysis = aiService.analyzeTicket("L'imprimante du bureau est bloquée et c'est urgent");

        assertEquals("Matériel", analysis.getCategory());
        assertEquals("Haute", analysis.getPriority());
    }
}
