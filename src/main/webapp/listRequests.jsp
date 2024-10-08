<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.smartbank.entities.CreditRequest" %>
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
            margin: 20px;
            background-color: #f9f9f9;
        }
        .container {
            max-width: 1200px;
            margin: auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            margin-bottom: 20px;
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        label {
            font-weight: bold;
        }
        select, input[type="date"], button {
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-size: 16px;
        }
        button {
            background-color: #28a745;
            color: white;
            cursor: pointer;
            border: none;
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
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        @media (max-width: 600px) {
            table, thead, tbody, th, td, tr {
                display: block;
            }
            th, td {
                padding: 10px;
                box-sizing: border-box;
                border: none;
                border-bottom: 1px solid #ddd;
            }
        }
    </style>
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
        <input type="date" name="startDate" id="startDate" placeholder="Start Date" />

        <label for="endDate">End Date:</label>
        <input type="date" name="endDate" id="endDate" placeholder="End Date" />

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
            if (creditRequests != null) {
                for (CreditRequest creditRequest : creditRequests) {
        %>
        <tr>
            <td><%= creditRequest.getId() %></td>
            <td><%= creditRequest.getProfession() %></td>
            <td><%= creditRequest.getProject() %></td>
            <td><%= creditRequest.getAmount() %></td>
            <td><%= creditRequest.getStatus() %></td>
            <td><%= creditRequest.getCreatedAt().toString() %></td>
            <td><%= creditRequest.getUpdatedAt().toString() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/creditRequestDetails?id=<%= creditRequest.getId() %>">View Details</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="8">No credit requests found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
