<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smartbank.entities.CreditRequest" %>
<%@ page import="com.smartbank.entities.History" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de la demande de crédit</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1, h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Détails de la demande de crédit</h1>

    <%
        CreditRequest creditRequest = (CreditRequest) request.getAttribute("creditRequest");
        if (creditRequest != null) {
    %>
    <table>
        <tr><th>ID</th><td><%= creditRequest.getId() != null ? creditRequest.getId() : "Non défini" %></td></tr>
        <tr><th>Profession</th><td><%= creditRequest.getProfession() != null ? creditRequest.getProfession() : "Non définie" %></td></tr>
        <tr><th>Projet</th><td><%= creditRequest.getProject() != null ? creditRequest.getProject() : "Non défini" %></td></tr>
        <tr><th>Montant</th><td><%= creditRequest.getAmount() != null ? creditRequest.getAmount() : "Non défini" %></td></tr>
        <tr><th>Durée</th><td><%= creditRequest.getDuration() %> mois</td></tr>
        <tr><th>Paiements mensuels</th><td><%= creditRequest.getMonthlyPayments() != null ? creditRequest.getMonthlyPayments() : "Non définis" %></td></tr>
        <tr><th>Email</th><td><%= creditRequest.getEmail() != null ? creditRequest.getEmail() : "Non défini" %></td></tr>
        <tr><th>Téléphone</th><td><%= creditRequest.getMobilePhone() != null ? creditRequest.getMobilePhone() : "Non défini" %></td></tr>
        <tr><th>Civilité</th><td><%= creditRequest.getCivilite() != null ? creditRequest.getCivilite() : "Non définie" %></td></tr>
        <tr><th>Prénom</th><td><%= creditRequest.getFirstName() != null ? creditRequest.getFirstName() : "Non défini" %></td></tr>
        <tr><th>Nom</th><td><%= creditRequest.getLastName() != null ? creditRequest.getLastName() : "Non défini" %></td></tr>
        <tr><th>Numéro CIN</th><td><%= creditRequest.getCinNumber() != null ? creditRequest.getCinNumber() : "Non défini" %></td></tr>
        <tr><th>Date de naissance</th><td><%= creditRequest.getBirthDate() != null ? creditRequest.getBirthDate() : "Non définie" %></td></tr>
        <tr><th>Date d'embauche</th><td><%= creditRequest.getHiringDate() %></td></tr>
        <tr><th>Revenu total</th><td><%= creditRequest.getTotalRevenue() %></td></tr>
        <tr><th>Crédits en cours</th><td><%= creditRequest.isHasOngoingCredits() ? "Oui" : "Non" %></td></tr>
        <tr><th>Statut actuel</th><td><%= creditRequest.getCurrentStatus() != null ? creditRequest.getCurrentStatus().getName() : "PENDING" %></td></tr>
        <tr><th>Date de création</th><td><%= creditRequest.getCreatedAt() %></td></tr>
        <tr><th>Dernière mise à jour</th><td><%= creditRequest.getUpdatedAt() %></td></tr>
    </table>

    <h2>Historique des modifications</h2>
    <table>
        <thead>
        <tr>
            <th>Date</th>
            <th>Statut</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<History> histories = creditRequest.getHistories();
            if (histories != null && !histories.isEmpty()) {
                for (History history : histories) {
        %>
        <tr>
            <td><%= history.getChangeDate() %></td>
            <td><%= history.getStatus().getName() %></td>
            <td><%= history.getDescription() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3">Aucun historique disponible</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>
    <p>Aucune demande de crédit trouvée.</p>
    <%
        }
    %>

    <a href="${pageContext.request.contextPath}/creditRequests" style="display: inline-block; margin-top: 20px; padding: 10px 15px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px;">Retour à la liste</a>
</div>
</body>
</html>