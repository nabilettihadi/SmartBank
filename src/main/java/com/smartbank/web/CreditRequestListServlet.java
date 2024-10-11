package com.smartbank.web;

import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.Status;
import com.smartbank.repositories.StatusHistoryRepository;
import com.smartbank.repositories.StatusRepository;
import com.smartbank.services.CreditRequestService;

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
    private StatusHistoryRepository statusHistoryRepository;

    @Inject
    private StatusRepository statusRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String statusParam = request.getParameter("status");
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");

        LocalDate startDate = (startDateParam != null && !startDateParam.isEmpty()) ? LocalDate.parse(startDateParam) : null;
        LocalDate endDate = (endDateParam != null && !endDateParam.isEmpty()) ? LocalDate.parse(endDateParam) : null;

        List<CreditRequest> creditRequests;

        if (statusParam != null && !statusParam.isEmpty()) {
            Status status = Status.valueOf(statusParam.toUpperCase());
            creditRequests = creditRequestService.getFilteredCreditRequests(status, startDate, endDate);
        } else {
            creditRequests = creditRequestService.getAllCreditRequests();
        }

        request.setAttribute("creditRequests", creditRequests);
        request.getRequestDispatcher("/listRequests.jsp").forward(request, response);
    }
}
