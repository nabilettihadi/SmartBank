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
        String statusFilter = request.getParameter("status");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        LocalDate startDate = null;
        LocalDate endDate = null;

        if (startDateStr != null && !startDateStr.isEmpty()) {
            try {
                startDate = LocalDate.parse(startDateStr);
            } catch (Exception e) {
                // Log l'erreur ou gérez-la comme vous le souhaitez
            }
        }

        if (endDateStr != null && !endDateStr.isEmpty()) {
            try {
                endDate = LocalDate.parse(endDateStr);
            } catch (Exception e) {
                // Log l'erreur ou gérez-la comme vous le souhaitez
            }
        }

        List<CreditRequest> creditRequests;
        if (statusFilter != null && !statusFilter.isEmpty()) {
            Status status = statusService.getStatusByName(statusFilter);
            creditRequests = creditRequestService.getFilteredCreditRequests(status, startDate, endDate);
        } else {
            creditRequests = creditRequestService.getAllCreditRequests();
        }

        List<Status> allStatuses = statusService.getAllStatuses();

        request.setAttribute("creditRequests", creditRequests);
        request.setAttribute("allStatuses", allStatuses);
        request.getRequestDispatcher("/WEB-INF/views/listRequests.jsp").forward(request, response);
    }
}