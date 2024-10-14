package com.smartbank.web;

import com.smartbank.services.CreditRequestService;
import com.smartbank.services.StatusService;
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

    @Inject
    private StatusService statusService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long requestId = Long.parseLong(request.getParameter("id"));
        Long statusId = Long.parseLong(request.getParameter("statusId"));
        String description = request.getParameter("description");

        try {
            creditRequestService.updateCreditRequestStatus(requestId, statusId, description);
            response.sendRedirect(request.getContextPath() + "/creditRequests");
        } catch (Exception e) {
            request.setAttribute("error", "Erreur lors de la mise à jour du statut : " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}