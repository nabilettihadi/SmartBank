package com.smartbank.web;

import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.Status;
import com.smartbank.services.CreditRequestService;
import com.smartbank.services.StatusService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "CreditRequestListServlet", value = "/creditRequests")
public class CreditRequestListServlet extends HttpServlet {

    @Inject
    private CreditRequestService creditRequestService;

    @Inject
    private StatusService statusService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String statusParam = request.getParameter("status");
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");

        LocalDate startDate = (startDateParam != null && !startDateParam.isEmpty()) ? LocalDate.parse(startDateParam) : null;
        LocalDate endDate = (endDateParam != null && !endDateParam.isEmpty()) ? LocalDate.parse(endDateParam) : null;

        List<CreditRequest> creditRequests;

        try {
            Status status = null;
            if (statusParam != null && !statusParam.isEmpty()) {
                status = statusService.getStatusByName(statusParam.toUpperCase());
            }

            creditRequests = creditRequestService.getFilteredCreditRequests(status, startDate, endDate);

            request.setAttribute("creditRequests", creditRequests);
            request.setAttribute("statuses", statusService.getAllStatuses());
            request.getRequestDispatcher("/WEB-INF/views/listRequests.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving credit requests: " + e.getMessage());
        }
    }
}