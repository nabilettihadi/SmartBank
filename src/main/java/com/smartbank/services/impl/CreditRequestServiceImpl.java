package com.smartbank.services.impl;

import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.Status;
import com.smartbank.entities.History;
import com.smartbank.repositories.CreditRequestRepository;
import com.smartbank.repositories.StatusRepository;
import com.smartbank.repositories.StatusHistoryRepository;
import com.smartbank.services.CreditRequestService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class CreditRequestServiceImpl implements CreditRequestService {

    @Inject
    private CreditRequestRepository creditRequestRepository;

    @Inject
    private StatusHistoryRepository statusHistoryRepository;

    @Inject
    private StatusRepository statusRepository;


    @Override
    @Transactional
    public CreditRequest createCreditRequest(CreditRequest creditRequest) {
        try {

            Status status = statusRepository.findByName("PENDING");
            if (status == null) {
                status = new Status("PENDING");
                statusRepository.save(status);
            }


            creditRequest.setStatus(status);

            return creditRequestRepository.save(creditRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create credit request: " + e.getMessage());
        }
    }

    @Override
    public CreditRequest getCreditRequestById(Long id) {
        return creditRequestRepository.findById(id);
    }

    @Override
    @Transactional
    public CreditRequest updateCreditRequest(CreditRequest creditRequest) {
        return creditRequestRepository.update(creditRequest);
    }

    @Override
    @Transactional
    public void deleteCreditRequest(Long id) {
        creditRequestRepository.deleteById(id);
    }

    @Override
    public List<CreditRequest> getAllCreditRequests() {
        return creditRequestRepository.findAll();
    }

    @Override
    public List<CreditRequest> getFilteredCreditRequests(Status status, LocalDate startDate, LocalDate endDate) {
        return creditRequestRepository.findFiltered(status, startDate, endDate);
    }

    @Override
    @Transactional
    public void updateCreditRequestStatus(Long requestId, Status newStatus, String description) {
        CreditRequest creditRequest = creditRequestRepository.findById(requestId);
        if (creditRequest != null) {

            creditRequest.setStatus(newStatus);
            creditRequest.setUpdatedAt(LocalDate.now());

            History history = new History(creditRequest, newStatus, description);
            statusHistoryRepository.save(history);

            creditRequestRepository.update(creditRequest);
        }
    }
}
