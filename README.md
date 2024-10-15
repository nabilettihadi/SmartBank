# Système de Demande de Crédit SmartBank

## Description
Le Système de Demande de Crédit SmartBank est une application web Java qui permet aux utilisateurs de soumettre, gérer et traiter des demandes de crédit. Elle offre une interface conviviale pour les clients souhaitant demander des prêts et pour les employés de la banque qui doivent examiner et gérer ces demandes.

## Fonctionnalités
- **Soumission de demandes de crédit** : Les utilisateurs peuvent soumettre des demandes de crédit via une interface web.
- **Gestion des demandes de crédit** : Les employés peuvent créer, mettre à jour, supprimer et consulter les demandes de crédit.
- **Suivi du statut des demandes** : Les demandes de crédit peuvent être suivies et leur statut mis à jour.
- **Simulation de crédit** : Les utilisateurs peuvent simuler un crédit avant de soumettre une demande.

## Technologies utilisées
- Java
- Jakarta EE
- JPA (Java Persistence API)
- Servlets
- JSP (JavaServer Pages)
- JSTL (JSP Standard Tag Library)
- HTML/CSS
- JavaScript
- Maven (pour la gestion des dépendances)
- JUnit et Mockito (pour les tests)

## Structure du projet
Le projet suit une structure Maven standard :

src/                                                                                                                                                                                 
├── main/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
│ ├── java/                                                                                                                                                                                                                                                                                                                                                                  
│ │ └── com/                                                                                                                                                                                 
│ │ └── smartbank/                                                                                                                                                                                 
│ │ ├── entities/                                                                                                                                                                                 
│ │ ├── repositories/                                                                                                                                                                                 
│ │ ├── services/                                                                                                                                                                                 
│ │ └── web/                                                                                                                                                                                 
│ ├── resources/                                                                                                                                                                                 
│ └── webapp/                                                                                                                                                                                 
│ ├── WEB-INF/                                                                                                                                                                                 
│ │ └── views/                                                                                                                                                                                 
│ ├── css/                                                                                                                                                                                 
│ └── js/                                                                                                                                                                                 
└── test/                                                                                                                                                                                  

## Composants clés
1. **Entités** : Représentent les objets principaux du système, comme `CreditRequest`, `Status`, et `History`.
2. **Services** : Logique métier pour gérer les opérations sur les demandes de crédit.
3. **Repositories** : Accès aux données pour les entités.
4. **Servlets** : Gèrent les requêtes HTTP et les interactions utilisateur.

## Installation et configuration
1. Clonez le dépôt.
2. Assurez-vous d'avoir Java JDK 11+ et Maven installés.
3. Naviguez vers le répertoire du projet.
4. Exécutez `mvn clean install` pour construire le projet.
5. Déployez le fichier WAR généré sur votre serveur d'applications compatible Jakarta EE.

## Exécution des tests
Pour exécuter les tests, utilisez la commande suivante :
`mvn test`

## Utilisation
Après avoir déployé l'application :
1. Accédez à la page principale pour commencer une simulation de crédit.
2. Remplissez les informations requises dans le formulaire en plusieurs étapes.
3. Soumettez la demande de crédit.
4. Les employés de la banque peuvent accéder à l'interface de gestion pour examiner et traiter les demandes de crédit.

## Références de code
- **index.jsp** : Page principale pour la simulation de crédit.
  ```src/main/webapp/index.jsp
  startLine: 1
  endLine: 159
  ```
- **CreditRequestServiceImpl.java** : Implémente la logique métier pour les demandes de crédit.
  ```java:src/main/java/com/smartbank/services/impl/CreditRequestServiceImpl.java
  startLine: 36
  endLine: 109
  ```
- **details.jsp** : Affiche les détails d'une demande de crédit.
  ```src/main/webapp/WEB-INF/views/details.jsp
  startLine: 1
  endLine: 106
  ```
- **CreditRequestStatusServlet.java** : Servlet pour mettre à jour le statut des demandes de crédit.
  ```java:src/main/java/com/smartbank/web/CreditRequestStatusServlet.java
  startLine: 1
  endLine: 37
  ```
- **CreditSimulationServlet.java** : Gère la création de demandes de crédit à partir des simulations.
  ```java:src/main/java/com/smartbank/web/CreditSimulationServlet.java
  startLine: 1
  endLine: 77
  ```
- **listRequests.jsp** : Affiche la liste des demandes de crédit.
  ```src/main/webapp/WEB-INF/views/listRequests.jsp
  startLine: 130
  endLine: 194
  ```

## Contribution
Les contributions sont les bienvenues. Veuillez forker le dépôt et créer une pull request avec vos modifications.
