package com.smartbank.web;

import com.smartbank.entities.CreditRequest;
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
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet(name = "CreditSimulationServlet", value = "/creditRequest")
public class CreditSimulationServlet extends HttpServlet {

    private CreditRequestService creditRequestService;

    @Override
    public void init() throws ServletException {
        CreditRequestRepository creditRequestRepository = new CreditRequestRepositoryImpl();
        creditRequestService = new CreditRequestServiceImpl(creditRequestRepository);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String step = request.getParameter("step");

        CreditRequest creditRequest;


        if (request.getSession().getAttribute("creditRequest") == null) {
            creditRequest = new CreditRequest();
        } else {
            creditRequest = (CreditRequest) request.getSession().getAttribute("creditRequest");
        }

        switch (step) {
            case "step1":
                creditRequest.setAmount(new BigDecimal(request.getParameter("amount")));
                creditRequest.setDuration(Integer.parseInt(request.getParameter("duration")));
                creditRequest.setMonthlyPayments(new BigDecimal(request.getParameter("monthly")));
                request.getSession().setAttribute("creditRequest", creditRequest);
                request.getRequestDispatcher("/step2.jsp").forward(request, response);

                System.out.println(creditRequest.getAmount());
                break;

            case "step2":
                creditRequest.setEmail(request.getParameter("email"));
                creditRequest.setMobilePhone(request.getParameter("phone"));
                request.getSession().setAttribute("creditRequest", creditRequest);
                request.getRequestDispatcher("/step3.jsp").forward(request, response);
                break;

            case "step3":
                creditRequest.setFirstName(request.getParameter("prenom"));
                creditRequest.setLastName(request.getParameter("nom"));
                creditRequest.setCinNumber(request.getParameter("cin"));
                creditRequest.setBirthDate(LocalDate.parse(request.getParameter("date-naissance")));
                creditRequest.setHiringDate(LocalDate.parse(request.getParameter("date-embauche")));
                creditRequest.setTotalRevenue(new BigDecimal(request.getParameter("revenus")));
                creditRequest.setHasOngoingCredits(request.getParameter("credit").equals("oui"));

                creditRequestService.createCreditRequest(creditRequest);

                request.getSession().removeAttribute("creditRequest");
                response.sendRedirect("success.jsp");
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Étape non reconnue.");
                break;
        }
    }
}