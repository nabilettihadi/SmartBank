package com.smartbank.web;

import com.smartbank.entities.Status;
import com.smartbank.services.CreditRequestService;
import com.smartbank.services.StatusService;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreditRequestStatusServlet", value = "/updateCreditRequestStatus")
public class CreditRequestStatusServlet extends HttpServlet {

    @Inject
    private CreditRequestService creditRequestService;

    @Inject
    private StatusService statusService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long requestId;
        String statusParam;

        try {
            requestId = Long.parseLong(request.getParameter("id"));
            statusParam = request.getParameter("status");

            if (statusParam == null || statusParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Status parameter is missing");
                return;
            }

            Status newStatus = statusService.getStatusByName(statusParam);
            if (newStatus == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid status value: " + statusParam);
                return;
            }

            creditRequestService.updateCreditRequestStatus(requestId, newStatus, "Statut mis à jour.");

            response.sendRedirect(request.getContextPath() + "/creditRequests");

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request ID: " + e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the status: " + e.getMessage());
        }
    }
}