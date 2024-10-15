<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <jsp:useBean id="creditRequest" scope="request" type="com.smartbank.entities.CreditRequest"/>

    <c:choose>
        <c:when test="${not empty creditRequest}">
            <table>
                <tr><th>ID</th><td>${creditRequest.id != null ? creditRequest.id : 'Non défini'}</td></tr>
                <tr><th>Profession</th><td>${creditRequest.profession != null ? creditRequest.profession : 'Non définie'}</td></tr>
                <tr><th>Projet</th><td>${creditRequest.project != null ? creditRequest.project : 'Non défini'}</td></tr>
                <tr><th>Montant</th><td>${creditRequest.amount != null ? creditRequest.amount : 'Non défini'}</td></tr>
                <tr><th>Durée</th><td>${creditRequest.duration} mois</td></tr>
                <tr><th>Paiements mensuels</th><td>${creditRequest.monthlyPayments != null ? creditRequest.monthlyPayments : 'Non définis'}</td></tr>
                <tr><th>Email</th><td>${creditRequest.email != null ? creditRequest.email : 'Non défini'}</td></tr>
                <tr><th>Téléphone</th><td>${creditRequest.mobilePhone != null ? creditRequest.mobilePhone : 'Non défini'}</td></tr>
                <tr><th>Civilité</th><td>${creditRequest.civilite != null ? creditRequest.civilite : 'Non définie'}</td></tr>
                <tr><th>Prénom</th><td>${creditRequest.firstName != null ? creditRequest.firstName : 'Non défini'}</td></tr>
                <tr><th>Nom</th><td>${creditRequest.lastName != null ? creditRequest.lastName : 'Non défini'}</td></tr>
                <tr><th>Numéro CIN</th><td>${creditRequest.cinNumber != null ? creditRequest.cinNumber : 'Non défini'}</td></tr>
                <tr><th>Date de naissance</th><td>${creditRequest.birthDate != null ? creditRequest.birthDate : 'Non définie'}</td></tr>
                <tr><th>Date d'embauche</th><td>${creditRequest.hiringDate}</td></tr>
                <tr><th>Revenu total</th><td>${creditRequest.totalRevenue}</td></tr>
                <tr><th>Crédits en cours</th><td>${creditRequest.hasOngoingCredits ? 'Oui' : 'Non'}</td></tr>
                <tr><th>Statut actuel</th><td>${creditRequest.currentStatus != null ? creditRequest.currentStatus.name : 'Non définie'}</td></tr>
                <tr><th>Date de création</th><td>${creditRequest.createdAt}</td></tr>
                <tr><th>Dernière mise à jour</th><td>${creditRequest.updatedAt}</td></tr>
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
                <c:forEach var="history" items="${creditRequest.histories}">
                    <tr>
                        <td>${history.changeDate}</td>
                        <td>${history.status.name}</td>
                        <td>${history.description}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty creditRequest.histories}">
                    <tr>
                        <td colspan="3">Aucun historique disponible</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Aucune demande de crédit trouvée.</p>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/creditRequests" style="display: inline-block; margin-top: 20px; padding: 10px 15px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px;">Retour à la liste</a>
</div>
</body>
</html>