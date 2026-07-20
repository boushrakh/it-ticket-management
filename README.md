# IT Ticket Management


## Membres de l'équipe
- Nafha KARMAS
- Bouchra KHARKHOUCHE

## Technologies
-Back-end : Spring Boot
-Front-end : React
-Base de données : PostgreSQL
-Sécurité : Spring Security + JWT
-Intelligence Artificielle : Microservice Python (FastAPI) utilisant un LLM (OpenAI GPT / Gemini)
-Conteneurisation : Docker
-Gestion de versions : Git / GitHub

## Architecture
```text
    Frontend(React)
           |
    Backend(Spring Boot)
           |
    PostgreSQL

    Backend <-> Service IA (FastAPI)
```

## Structure du projet

```text
IT-Ticket-Management
|
|-frontend/
|-backend/
|-app/
|-tests/
|-docker-compose.yml
|-README.md
|-.gitignore
|-.env.example
```

## Prérequis
- Java 26
- Maven
- Node.js
- Docker(en attente d'installation)
- PostgreSQL(via Docker)
- Git

> **Remarque :** La configuration de Docker et de PostgreSQL sera finalisé après la validation et installation des outils par l'administration.

## Installation et lancement

### Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```
### Frontend

```bash
cd frontend
npm install
npm run dev
```

## Exécution des tests

### Tests Backend

```bash
cd backend
mvn test
```

## Conventions Git

### Branches
- main : version stable
- dev : branche de développement
- feature/nom-fonctionnalite : nouvelle fonctionnalité
- bugfix/nom-bug : correction de bug

Exemples :
feature/login
feature/dashboard
bugfix/auth-error

### Commits
- feat: ajout d'une fonctionnalité
- fix: correction d'un bug
- docs: modification de la documentation
- test: ajout de tests

Exemples :
feat: ajout authentification JWT
fix: correction erreur Docker
docs: mise à jour README

### Pull Requests
Titre :
[FEATURE] Ajout du module Login

Description :
- Fonctionnalités ajoutées
- Fichiers modifiés
- Tests effectués
