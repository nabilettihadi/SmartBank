package com.smartbank.web;

import com.smartbank.entities.CreditRequest;
import com.smartbank.services.CreditRequestService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreditRequestDetailsServlet", value = "/creditRequestDetails")
public class CreditRequestDetailsServlet extends HttpServlet {

    @Inject
    private CreditRequestService creditRequestService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Credit request ID is required");
            return;
        }

        try {
            Long id = Long.parseLong(idParam);
            CreditRequest creditRequest = creditRequestService.getCreditRequestById(id);
            if (creditRequest == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Credit request not found");
                return;
            }

            request.setAttribute("creditRequest", creditRequest);
            request.getRequestDispatcher("/WEB-INF/views/details.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid credit request ID");
        }
    }
}