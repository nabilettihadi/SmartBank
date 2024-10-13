package com.smartbank.services.impl;

import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.History;
import com.smartbank.entities.Status;
import com.smartbank.repositories.CreditRequestRepository;
import com.smartbank.repositories.StatusHistoryRepository;
import com.smartbank.repositories.StatusRepository;
import com.smartbank.services.CreditRequestService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class CreditRequestServiceImpl implements CreditRequestService {

    private static final Logger LOGGER = Logger.getLogger(CreditRequestServiceImpl.class.getName());

    @Inject
    private CreditRequestRepository creditRequestRepository;

    @Inject
    private StatusRepository statusRepository;

    @Inject
    private StatusHistoryRepository statusHistoryRepository;

    @Transactional
    @Override
    public CreditRequest createCreditRequest(CreditRequest creditRequest) {
        try {
            LOGGER.info("Attempting to create credit request: " + creditRequest);

            Status pendingStatus = statusRepository.findByName("PENDING");
            if (pendingStatus == null) {
                LOGGER.info("PENDING status not found, creating new one");
                pendingStatus = new Status("PENDING");
                pendingStatus = statusRepository.save(pendingStatus);
            }

            creditRequest.setCreatedAt(LocalDate.now());
            creditRequest.setCurrentStatus(pendingStatus);

            LOGGER.info("Saving credit request: " + creditRequest);
            CreditRequest savedRequest = creditRequestRepository.save(creditRequest);
            LOGGER.info("Credit request saved successfully: " + savedRequest);

            History initialHistory = new History(savedRequest, pendingStatus, "Demande de crédit créée");
            statusHistoryRepository.save(initialHistory);

            return savedRequest;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create credit request", e);
            throw new RuntimeException("Failed to create credit request: " + e.getMessage(), e);
        }
    }

    public CreditRequest getCreditRequestById(Long id) {
        return creditRequestRepository.findById(id);
    }

    @Transactional
    public CreditRequest updateCreditRequest(CreditRequest creditRequest) {
        return creditRequestRepository.update(creditRequest);
    }

    @Transactional
    public void deleteCreditRequest(Long id) {
        creditRequestRepository.deleteById(id);
    }

    public List<CreditRequest> getAllCreditRequests() {
        return creditRequestRepository.findAll();
    }

    public List<CreditRequest> getFilteredCreditRequests(Status status, LocalDate startDate, LocalDate endDate) {
        return creditRequestRepository.findFiltered(status, startDate, endDate);
    }

    @Transactional
    public void updateCreditRequestStatus(Long requestId, Status newStatus, String description) {
        CreditRequest creditRequest = creditRequestRepository.findById(requestId);
        if (creditRequest != null) {
            creditRequest.setCurrentStatus(newStatus);
            creditRequest.setUpdatedAt(LocalDate.now());
            creditRequestRepository.update(creditRequest);
        } else {
            throw new IllegalArgumentException("Credit request with ID " + requestId + " not found.");
        }
    }
}