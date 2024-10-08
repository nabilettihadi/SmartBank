package com.smartbank.services;

import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.CreditRequestStatus;

import java.time.LocalDate;
import java.util.List;

public interface CreditRequestService {

    CreditRequest createCreditRequest(CreditRequest creditRequest);

    CreditRequest getCreditRequestById(Long id);

    CreditRequest updateCreditRequest(CreditRequest creditRequest);

    void deleteCreditRequest(Long id);

    List<CreditRequest> getAllCreditRequests();

    List<CreditRequest> getFilteredCreditRequests(CreditRequestStatus status, LocalDate startDate, LocalDate endDate);

}
