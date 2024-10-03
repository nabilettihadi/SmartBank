package com.smartbank.services;

import com.smartbank.entities.CreditRequest;

import java.util.List;

public interface CreditRequestService {

    CreditRequest createCreditRequest(CreditRequest creditRequest);

    CreditRequest getCreditRequestById(Long id);

    CreditRequest updateCreditRequest(CreditRequest creditRequest);

    void deleteCreditRequest(Long id);

    List<CreditRequest> getAllCreditRequests();

}
