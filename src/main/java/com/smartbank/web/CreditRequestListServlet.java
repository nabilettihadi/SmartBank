package com.smartbank.web;

import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.CreditRequestStatus;
import com.smartbank.services.CreditRequestService;
import com.smartbank.services.impl.CreditRequestServiceImpl;
import com.smartbank.repositories.CreditRequestRepository;
import com.smartbank.repositories.impl.CreditRequestRepositoryImpl;

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

    private CreditRequestService creditRequestService;

    public CreditRequestListServlet() {
        CreditRequestRepository creditRequestRepository = new CreditRequestRepositoryImpl();
        creditRequestService = new CreditRequestServiceImpl(creditRequestRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String statusParam = request.getParameter("status");
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");

        LocalDate startDate = (startDateParam != null && !startDateParam.isEmpty()) ? LocalDate.parse(startDateParam) : null;
        LocalDate endDate = (endDateParam != null && !endDateParam.isEmpty()) ? LocalDate.parse(endDateParam) : null;

        List<CreditRequest> creditRequests;

        if (statusParam != null || startDate != null || endDate != null) {
            CreditRequestStatus status = null;
            if (statusParam != null && !statusParam.isEmpty()) {
                status = CreditRequestStatus.valueOf(statusParam);
            }
            creditRequests = creditRequestService.getFilteredCreditRequests(status, startDate, endDate);
        } else {
            creditRequests = creditRequestService.getAllCreditRequests();
        }

        request.setAttribute("creditRequests", creditRequests);
        request.getRequestDispatcher("/listRequests.jsp").forward(request, response);
    }
}
