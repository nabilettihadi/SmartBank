package com.smartbank.web;

import com.smartbank.entities.Status;
import com.smartbank.services.CreditRequestService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreditRequestStatusServlet", value = "/updateCreditRequestStatus")
public class CreditRequestStatusServlet extends HttpServlet {

    @Inject
    private CreditRequestService creditRequestService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long requestId;
        String statusParam;

        try {

            requestId = Long.parseLong(request.getParameter("id"));
            statusParam = request.getParameter("status");

            if (statusParam == null || statusParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Status parameter is missing");
                return;
            }

            Status newStatus = Status.valueOf(statusParam.toUpperCase());

            creditRequestService.updateCreditRequestStatus(requestId, newStatus, "Statut mis à jour.");

            response.sendRedirect(request.getContextPath() + "/creditRequests");

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid status value: " + e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the status: " + e.getMessage());
        }
    }
}
