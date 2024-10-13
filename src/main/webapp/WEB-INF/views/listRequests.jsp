<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.smartbank.entities.CreditRequest" %>
<%@ page import="com.smartbank.entities.Status" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Credit Requests</title>
</head>
<body>
<h1>Credit Requests</h1>

<form action="${pageContext.request.contextPath}/creditRequests" method="get">
    <label for="status">Status:</label>
    <select name="status" id="status">
        <option value="">All</option>
        <% for (Status status : (List<Status>)request.getAttribute("statuses")) { %>
        <option value="<%= status.getName() %>"><%= status.getName() %></option>
        <% } %>
    </select>

    <label for="startDate">Start Date:</label>
    <input type="date" name="startDate" id="startDate">

    <label for="endDate">End Date:</label>
    <input type="date" name="endDate" id="endDate">

    <input type="submit" value="Filter">
</form>

<table border="1">
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
    <%
        List<CreditRequest> creditRequests = (List<CreditRequest>)request.getAttribute("creditRequests");
        for (CreditRequest cr : creditRequests) {
    %>
    <tr>
        <td><%= cr.getId() %></td>
        <td><%= cr.getProfession() %></td>
        <td><%= cr.getProject() %></td>
        <td><%= cr.getAmount() %></td>
        <td>
            <form action="${pageContext.request.contextPath}/updateCreditRequestStatus" method="post">
                <input type="hidden" name="id" value="<%= cr.getId() %>">
                <select name="status" onchange="this.form.submit()">
                    <% for (Status status : (List<Status>)request.getAttribute("statuses")) { %>
                    <option value="<%= status.getName() %>" <%= cr.getCurrentStatus().getName().equals(status.getName()) ? "selected" : "" %>><%= status.getName() %></option>
                    <% } %>
                </select>
            </form>
        </td>
        <td><%= cr.getCreatedAt() %></td>
        <td><%= cr.getUpdatedAt() != null ? cr.getUpdatedAt() : "N/A" %></td>
        <td>
            <a href="${pageContext.request.contextPath}/creditRequestDetails?id=<%= cr.getId() %>">View Details</a>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>