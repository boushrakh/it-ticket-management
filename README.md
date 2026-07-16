# IT Ticket Management

## Description
Plateforme de gestion des incidents et tickets IT intégrant une intelligence artificielle permettant d'analyser automatiquement les tickets, de proposer une catégorie, un niveau de priorité et de générer un résumé.

## Membres de l'équipe
-Nafha KARMAS
-Bouchra KHARKHOUCHE

## Technologies
-Back-end : Spring Boot
-Front-end : React
-Base de données : PostgreSQL
-Sécurité : Spring Security + JWT
-Intelligence Artificielle : Python, FastAPI, LLM (OpenAI GPT / Gemini)
-Conteneurisation : Docker
-Gestion de versions : Git / GitHub

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
