package com.smartbank.web;

import com.smartbank.entities.CreditRequest;
import com.smartbank.services.CreditRequestService;
import com.smartbank.services.StatusService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet(name = "CreditSimulationServlet", value = "/creditRequest")
public class CreditSimulationServlet extends HttpServlet {

    @Inject
    private CreditRequestService creditRequestService;

    @Inject
    private StatusService statusService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CreditRequest creditRequest = createCreditRequest(request);
            CreditRequest savedRequest = creditRequestService.createCreditRequest(creditRequest);

            if (savedRequest != null && savedRequest.getId() != null) {
                response.sendRedirect(request.getContextPath() + "/creditRequests");
            } else {
                throw new Exception("Credit request was not saved successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            System.out.println("Full stack trace: " + stackTrace);
            request.setAttribute("error", "Error processing credit request: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private CreditRequest createCreditRequest(HttpServletRequest request) {
        CreditRequest creditRequest = new CreditRequest();

        creditRequest.setProfession(request.getParameter("profession"));
        creditRequest.setProject(request.getParameter("project"));
        creditRequest.setAmount(new BigDecimal(request.getParameter("amount")));
        creditRequest.setDuration(Integer.parseInt(request.getParameter("duration")));
        creditRequest.setMonthlyPayments(new BigDecimal(request.getParameter("monthly")));
        creditRequest.setEmail(request.getParameter("email"));
        creditRequest.setMobilePhone(request.getParameter("phone"));
        creditRequest.setCivilite(request.getParameter("civilite"));
        creditRequest.setFirstName(request.getParameter("prenom"));
        creditRequest.setLastName(request.getParameter("nom"));
        creditRequest.setCinNumber(request.getParameter("cin"));
        creditRequest.setBirthDate(LocalDate.parse(request.getParameter("date-naissance")));
        creditRequest.setHiringDate(LocalDate.parse(request.getParameter("date-embauche")));
        creditRequest.setTotalRevenue(new BigDecimal(request.getParameter("revenus")));
        creditRequest.setHasOngoingCredits("oui".equalsIgnoreCase(request.getParameter("credit")));

        return creditRequest;
    }
}