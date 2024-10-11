<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.smartbank.entities.CreditRequest" %>
<%@ page import="com.smartbank.entities.Status" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Credit Requests</title>
    <link rel="stylesheet" href="css/styles.css" />
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
    <script>
        function autoSubmit(form) {
            form.submit();
        }

        function setSelectColors() {
            const selects = document.querySelectorAll('select[name="status"]');
            selects.forEach(select => {
                const selectedValue = select.value;
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

        window.onload = setSelectColors;
    </script>
</head>
<body>
<div class="container">
    <h1>Credit Requests</h1>
    <form method="get" action="${pageContext.request.contextPath}/creditRequests">
        <label for="status">Status:</label>
        <select name="status" id="status">
            <option value="">All</option>
            <option value="PENDING">PENDING</option>
            <option value="ACCEPTED">ACCEPTED</option>
            <option value="REJECTED">REJECTED</option>
            <option value="CANCELED">CANCELED</option>
        </select>
        <label for="startDate">Start Date:</label>
        <input type="date" name="startDate" id="startDate" />
        <label for="endDate">End Date:</label>
        <input type="date" name="endDate" id="endDate" />
        <button type="submit">Filter</button>
    </form>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Profession</th>
            <th>Project</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Created At</th>
            <th>Updated At</th>
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
            <td data-label="Project"><%= creditRequest.getProject() %></td>
            <td data-label="Amount"><%= creditRequest.getAmount() %></td>
            <td data-label="Status">
                <form method="post" action="${pageContext.request.contextPath}/updateCreditRequestStatus" onchange="autoSubmit(this)">
                    <input type="hidden" name="id" value="<%= creditRequest.getId() %>">
                    <select name="status" class="status-select" onchange="autoSubmit(this.form)">
                        <option value="PENDING" <%= creditRequest.getStatus() != null && creditRequest.getStatus().getName().equals("PENDING") ? "selected" : "" %>>PENDING</option>
                        <option value="ACCEPTED" <%= creditRequest.getStatus() != null && creditRequest.getStatus().getName().equals("ACCEPTED") ? "selected" : "" %>>ACCEPTED</option>
                        <option value="REJECTED" <%= creditRequest.getStatus() != null && creditRequest.getStatus().getName().equals("REJECTED") ? "selected" : "" %>>REJECTED</option>
                        <option value="CANCELED" <%= creditRequest.getStatus() != null && creditRequest.getStatus().getName().equals("CANCELED") ? "selected" : "" %>>CANCELED</option>

                    </select>
                </form>
            </td>
            <td data-label="Created At"><%= creditRequest.getCreatedAt() %></td>
            <td data-label="Updated At"><%= creditRequest.getUpdatedAt() %></td>
            <td data-label="Actions">
                <a href="${pageContext.request.contextPath}/creditRequestDetails?id=<%= creditRequest.getId() %>">View Details</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="8" style="text-align: center;">No credit requests found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
