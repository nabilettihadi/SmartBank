<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.smartbank.entities.CreditRequest" %>
<%@ page import="com.smartbank.entities.Status" %>
<%@ page import="com.smartbank.entities.History" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Demandes de Crédit</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            padding: 20px;
        }
        .container {
            max-width: 1200px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        form label {
            margin-right: 10px;
        }
        form select, form input[type="date"], form button {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #28a745;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function updateStatus(form) {
            var description = prompt("Entrez une description pour ce changement de statut :");
            if (description != null && description.trim() !== "") {
                form.elements['description'].value = description;
                form.submit();
            }
        }

        function setSelectColors() {
            const selects = document.querySelectorAll('select[name="statusId"]');
            selects.forEach(select => {
                const selectedOption = select.options[select.selectedIndex];
                const selectedValue = selectedOption.text;
                switch (selectedValue) {
                    case 'PENDING':
                        select.style.backgroundColor = '#ffc107';
                        break;
                    case 'ACCEPTED':
                        select.style.backgroundColor = '#28a745';
                        break;
                    case 'REJECTED':
                        select.style.backgroundColor = '#dc3545';
                        break;
                    case 'CANCELED':
                        select.style.backgroundColor = '#6c757d';
                        break;
                    default:
                        select.style.backgroundColor = '#fff';
                }
            });
        }

        $(document).ready(function() {
            setSelectColors();
        });
    </script>
</head>
<body>
<div class="container">
    <h1>Demandes de Crédit</h1>
    <div style="text-align: right; margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/index.jsp" style="background-color: #007bff; color: white; padding: 10px 15px; text-decoration: none; border-radius: 5px;">Nouvelle demande de crédit</a>
    </div>
    <form method="get" action="${pageContext.request.contextPath}/creditRequests">
        <label for="status">Statut :</label>
        <select name="status" id="status">
            <option value="">Tous</option>
            <option value="PENDING">EN ATTENTE</option>
            <option value="ACCEPTED">ACCEPTÉ</option>
            <option value="REJECTED">REJETÉ</option>
            <option value="CANCELED">ANNULÉ</option>
        </select>
        <label for="startDate">Date de début :</label>
        <input type="date" name="startDate" id="startDate" />
        <label for="endDate">Date de fin :</label>
        <input type="date" name="endDate" id="endDate" />
        <button type="submit">Filtrer</button>
    </form>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Profession</th>
            <th>Projet</th>
            <th>Montant</th>
            <th>Statut</th>
            <th>Date de création</th>
            <th>Date de mise à jour</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<CreditRequest> creditRequests = (List<CreditRequest>) request.getAttribute("creditRequests");
            if (creditRequests != null && !creditRequests.isEmpty()) {
                for (CreditRequest creditRequest : creditRequests) {
        %>
        <tr>
            <td data-label="ID"><%= creditRequest.getId() %></td>
            <td data-label="Profession"><%= creditRequest.getProfession() %></td>
            <td data-label="Projet"><%= creditRequest.getProject() %></td>
            <td data-label="Montant"><%= creditRequest.getAmount() %></td>
            <td data-label="Statut">
                <form method="post" action="${pageContext.request.contextPath}/updateCreditRequestStatus">
                    <input type="hidden" name="id" value="<%= creditRequest.getId() %>">
                    <input type="hidden" name="description" value="">
                    <select name="statusId" class="status-select" onchange="updateStatus(this.form)">
                        <%
                            List<Status> allStatuses = (List<Status>) request.getAttribute("allStatuses");
                            if (allStatuses != null && !allStatuses.isEmpty()) {
                                for (Status status : allStatuses) {
                        %>
                        <option value="<%= status.getId() %>"
                                <%= creditRequest.getCurrentStatus() != null && creditRequest.getCurrentStatus().getId().equals(status.getId()) ? "selected" : "" %>>
                            <%= status.getName() %>
                        </option>
                        <%
                            }
                        } else {
                        %>
                        <option value="">Aucun statut disponible</option>
                        <% } %>
                    </select>
                </form>
            </td>
            <td data-label="Date de création"><%= creditRequest.getCreatedAt() %></td>
            <td data-label="Date de mise à jour"><%= creditRequest.getUpdatedAt() %></td>
            <td data-label="Actions">
                <a href="${pageContext.request.contextPath}/creditRequestDetails?id=<%= creditRequest.getId() %>">Voir les détails</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="8" style="text-align: center;">Aucune demande de crédit trouvée.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>