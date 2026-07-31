from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class TicketRequest(BaseModel):
    description: str

class AiResponse(BaseModel):
    category: str
    priority: str
    summary: str

@app.post("/analyze", response_model=AiResponse)
def analyze_ticket(request: TicketRequest):
    desc = request.description.lower()

    if any(w in desc for w in ["écran", "ecran", "souris", "clavier", "pc", "ordinateur"]):
        category = "Matériel"
    elif any(w in desc for w in ["logiciel", "application", "programme", "bug", "erreur", "mot de passe"]):
        category = "Logiciel"
    elif any(w in desc for w in ["réseau", "connexion", "wifi", "internet", "reseau", "serveur"]):
        category = "Réseau"
    else:
        category = "Autre"

    if any(w in desc for w in ["urgent", "bloque", "bloqué", "immédiat", "critique", "panne"]):
        priority = "Haute"  
    elif any(w in desc for w in ["lent", "probleme", "difficulté", "problème"]):
        priority = "Moyenne"
    else:
        priority = "Basse"

        summary = request.description[:57] + "..." if len(request.description) > 60 else request.description

        return {
            "category": category,
            "priority": priority,
            "summary": summary
        }