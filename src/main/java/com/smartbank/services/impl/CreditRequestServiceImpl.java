package com.smartbank.services.impl;

import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.CreditRequestStatus;
import com.smartbank.repositories.CreditRequestRepository;
import com.smartbank.services.CreditRequestService;

import java.time.LocalDate;
import java.util.List;

public class CreditRequestServiceImpl implements CreditRequestService {

    private final CreditRequestRepository creditRequestRepository;

    public CreditRequestServiceImpl(CreditRequestRepository creditRequestRepository) {
        this.creditRequestRepository = creditRequestRepository;
    }

    @Override
    public CreditRequest createCreditRequest(CreditRequest creditRequest) {
        return creditRequestRepository.save(creditRequest);
    }

    @Override
    public CreditRequest getCreditRequestById(Long id) {
        return creditRequestRepository.findById(id);
    }

    @Override
    public CreditRequest updateCreditRequest(CreditRequest creditRequest) {
        return creditRequestRepository.update(creditRequest);
    }

    @Override
    public void deleteCreditRequest(Long id) {
        creditRequestRepository.deleteById(id);
    }

    @Override
    public List<CreditRequest> getAllCreditRequests() {
        return creditRequestRepository.findAll();
    }

    @Override
    public List<CreditRequest> getFilteredCreditRequests(CreditRequestStatus status, LocalDate startDate, LocalDate endDate) {
        return creditRequestRepository.findFiltered(status, startDate, endDate);
    }
}
