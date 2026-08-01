package com.alten.backend.ai.service;

import com.alten.backend.ai.controller.AIController;
import com.alten.backend.ai.dto.AIdto;
import org.springframework.stereotype.Service;


@Service
public class AIService {

    private final AIController AIController;

    AIService(AIController AIController) {
        this.AIController = AIController;
    }

    public AIdto analyzeTicket(String ticketDescription) {
        if (ticketDescription == null || ticketDescription.isEmpty()) {
            return new AIdto("General", "Basse", "Aucune description fournie.");
        }

        String lowerDesc = ticketDescription.toLowerCase();
        String category = "Autre";
        String priority = "Moyenne";

        if (lowerDesc.contains("écran") || lowerDesc.contains("ecran") || lowerDesc.contains("souris") || lowerDesc.contains("clavier") || lowerDesc.contains("imprimante") || lowerDesc.contains("ordinateur") || lowerDesc.contains("pc") || lowerDesc.contains("clavier")) {
            category = "Matériel";
        } else if (lowerDesc.contains("wifi") || lowerDesc.contains("réseau") || lowerDesc.contains("reseau") || lowerDesc.contains("connexion") || lowerDesc.contains("internet")) {
            category = "Réseau";
        } else if (lowerDesc.contains("bug") || lowerDesc.contains("erreur") || lowerDesc.contains("logiciel") || lowerDesc.contains("application") || lowerDesc.contains("mot de passe") ) {
            category = "Logiciel";
        } else if (lowerDesc.contains("autre")) {
            category = "Autre";
        }

        if (lowerDesc.contains("urgent") || lowerDesc.contains("bloqué") || lowerDesc.contains("bloque") || lowerDesc.contains("panne générale") || lowerDesc.contains("critique")) {

            priority = "Haute";
        } else if (lowerDesc.contains("lent") || lowerDesc.contains("problème") || lowerDesc.contains("probleme") || lowerDesc.contains("bug")) {
            priority = "Moyenne";
        } else {
            priority = "Basse";
        }

        String summary = ticketDescription.length() > 60 ? ticketDescription.substring(0, 57) + "..." : ticketDescription;

        return new AIdto(category, priority, summary);
    }

}
