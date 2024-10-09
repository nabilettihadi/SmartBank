package com.smartbank.web;

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

@WebServlet(name = "CreditRequestStatusServlet", value = "/updateCreditRequestStatus")
public class CreditRequestStatusServlet extends HttpServlet {

    private CreditRequestService creditRequestService;

    @Override
    public void init() throws ServletException {
        CreditRequestRepository creditRequestRepository = new CreditRequestRepositoryImpl();
        creditRequestService = new CreditRequestServiceImpl(creditRequestRepository);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long requestId = Long.parseLong(request.getParameter("id"));
        CreditRequestStatus newStatus = CreditRequestStatus.valueOf(request.getParameter("status"));

        creditRequestService.updateCreditRequestStatus(requestId, newStatus);

        response.sendRedirect(request.getContextPath() + "/creditRequests");
    }
}
