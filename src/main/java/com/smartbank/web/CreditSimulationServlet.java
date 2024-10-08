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

        handleStep1(request);
        handleStep2(request);
        handleStep3(request,response);
    }

    private void handleStep1(HttpServletRequest request) {

        String profession = request.getParameter("profession");
        String project = request.getParameter("project");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        Integer duration = Integer.parseInt(request.getParameter("duration"));
        BigDecimal monthlyPayments = new BigDecimal(request.getParameter("monthly"));

        request.getSession().setAttribute("profession", profession);
        request.getSession().setAttribute("project", project);
        request.getSession().setAttribute("amount", amount);
        request.getSession().setAttribute("duration", duration);
        request.getSession().setAttribute("monthlyPayments", monthlyPayments);
    }

    private void handleStep2(HttpServletRequest request) {

        String email = request.getParameter("email");
        String mobilePhone = request.getParameter("phone");

        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("mobilePhone", mobilePhone);
    }

    private void handleStep3(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String civilite = request.getParameter("civilite");
        String firstName = request.getParameter("prenom");
        String lastName = request.getParameter("nom");
        String cinNumber = request.getParameter("cin");
        LocalDate birthDate = LocalDate.parse(request.getParameter("date-naissance"));
        LocalDate hiringDate = LocalDate.parse(request.getParameter("date-embauche"));
        BigDecimal totalRevenue = new BigDecimal(request.getParameter("revenus"));
        Boolean hasOngoingCredits = "oui".equals(request.getParameter("credit"));


        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setProfession((String) request.getSession().getAttribute("profession"));
        creditRequest.setProject((String) request.getSession().getAttribute("project"));
        creditRequest.setAmount((BigDecimal) request.getSession().getAttribute("amount"));
        creditRequest.setDuration((Integer) request.getSession().getAttribute("duration"));
        creditRequest.setMonthlyPayments((BigDecimal) request.getSession().getAttribute("monthlyPayments"));
        creditRequest.setEmail((String) request.getSession().getAttribute("email"));
        creditRequest.setMobilePhone((String) request.getSession().getAttribute("mobilePhone"));
        creditRequest.setCivilite(civilite);
        creditRequest.setFirstName(firstName);
        creditRequest.setLastName(lastName);
        creditRequest.setCinNumber(cinNumber);
        creditRequest.setBirthDate(birthDate);
        creditRequest.setHiringDate(hiringDate);
        creditRequest.setTotalRevenue(totalRevenue);
        creditRequest.setHasOngoingCredits(hasOngoingCredits);
        creditRequest.setStatus(CreditRequestStatus.PENDING);
        creditRequest.setUpdatedAt(LocalDate.now());

        creditRequestService.createCreditRequest(creditRequest);

        response.sendRedirect(request.getContextPath() + "/creditRequests");
    }
}
