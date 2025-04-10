# 🎉 API REST - Gestion d'Événements

## 📄 Description

Cette API REST permet la gestion d'événements. Elle offre des fonctionnalités pour créer, consulter, filtrer dynamiquement et trier des événements selon leur **catégorie**, leur **lieu**, leur **période**, etc.

L’API a été conçue selon les bonnes pratiques avec Java et Spring Boot : architecture REST, pagination, gestion des erreurs, requêtes dynamiques avec QueryDSL, et migrations de base de données avec Flyway.

## 🛠️ Technologies utilisées

- Java 21+
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- MySQL
- Flyway (migrations SQL)
- QueryDSL (filtres dynamiques)
- Lombok
- GRADLE

## 🧱 Modèle de données

Le projet comporte **4 entités principales** :

- `Evenement` : libelle, description, date, category et lieux.
- `Categorie` : type d’événement (concert, conférence, atelier…)
- `Periode` : période de l’événement (début / fin) et le statut
- `Lieu` : ville

**Relations entre entités :**
- Un `Evenement` est associé à **une Categorie**, **un Lieu**, et **une Periode**.

## ▶️ Lancer le projet

### ✅ Prérequis

- Java 21+
- Spring Boot 3.x
- Gradle
- Mysql

### 🏁 Étapes de lancement

1. **Cloner le projet**
   ```bash
   git clone https://github.com/Tmousnier/Infos_Evenements
   cd Infos_Evenements
