## 🧱 Objectif du TP

Ce TP a pour but d’apprendre à développer une application Spring Boot complète, utilisant Spring Data JPA et Spring Data REST, pour exposer automatiquement des services web RESTful permettant de gérer des comptes bancaires et leurs clients.
L’objectif principal est de comprendre comment Spring Data REST peut automatiser la création d’API sans avoir besoin de créer manuellement des contrôleurs.

## 🚀 Technologies utilisées

Spring Boot – Framework principal pour le démarrage et la configuration automatique du projet

Spring Data JPA – Gestion des entités et communication avec la base de données

Spring Data REST – Exposition automatique des repositories sous forme d’API REST

H2 Database – Base de données en mémoire utilisée pour les tests et le développement

Lombok – Génération automatique des getters, setters, constructeurs et toString

DevTools – Rechargement automatique lors des modifications de code

## 🏗️ Structure du projet

<img width="391" height="533" alt="image" src="https://github.com/user-attachments/assets/f7235bdd-8adf-4926-bbe5-33ed36b167af" />

## ⚙️ Fonctionnalités principales
**1️⃣ Création de la base et configuration**

Configuration dans application.properties pour utiliser H2, port 8082, et base path /api.

Console accessible sur : http://localhost:8082/h2-console

**2️⃣ Gestion des entités JPA**

Compte : contient id, solde, dateCreation, type, et un lien vers un Client.

Client : contient id, nom, email, et une liste de comptes.

Relation :

Client (1)  ←→  (n) Compte

**3️⃣ Initialisation des données**

Dans MsBanqueApplication, un CommandLineRunner insère des exemples de clients et comptes lors du démarrage.
Exemple :

Client “Amal” avec deux comptes

Client “Ali” avec un compte

**4️⃣ Exposition automatique avec Spring Data REST**

Les endpoints sont automatiquement générés à partir des repositories :

/api/clients

/api/comptes

/api/comptes/{id}/client

/api/clients/{id}/comptes

Chaque ressource contient des liens HAL (_links) pour naviguer entre clients et comptes.


<img width="522" height="946" alt="Capture d&#39;écran 2025-11-07 215809" src="https://github.com/user-attachments/assets/c0566050-d38e-4f67-9357-4cee69771e35" />


<img width="550" height="966" alt="Capture d&#39;écran 2025-11-07 220035" src="https://github.com/user-attachments/assets/1d8a9f3d-d642-4744-b4d8-7f5f5b0f90ff" />






**5️⃣ Projections personnalisées**

Deux projections (CompteProjection1, CompteProjection2) limitent les champs retournés dans les réponses JSON :

Projection1 (solde) : affiche uniquement le solde

Projection2 (mobile) : affiche le solde et le type

Usage :

## GET /api/comptes/1?projection=solde


<img width="615" height="964" alt="Capture d&#39;écran 2025-11-07 220234" src="https://github.com/user-attachments/assets/24a87836-22e5-4028-a14c-925c95610ba4" />

## GET /api/comptes/1?projection=mobile


<img width="637" height="415" alt="TP11 details" src="https://github.com/user-attachments/assets/a2adb0e2-9852-4b96-856d-25dcef724077" />

**6️⃣ Exposition des IDs**

Grâce à restConfiguration.exposeIdsFor(), les id des entités sont visibles dans les réponses JSON.

**7️⃣ Recherche personnalisée**

Une méthode de recherche a été ajoutée dans CompteRepository :

@RestResource(path = "byType")
List<Compte> findByType(@Param("t") TypeCompte type);


Elle permet de filtrer les comptes selon leur type :

## GET /api/comptes/search/byType?t=EPARGNE

<img width="595" height="845" alt="TP11 epargne" src="https://github.com/user-attachments/assets/f28559a5-ab27-4117-8949-0755e6fa0ae6" />
