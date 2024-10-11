package com.smartbank.repositories;

import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.Status;

import java.time.LocalDate;
import java.util.List;

public interface CreditRequestRepository {

    CreditRequest save(CreditRequest creditRequest);

    CreditRequest findById(Long id);

    CreditRequest update(CreditRequest creditRequest);

    boolean deleteById(Long id);

    List<CreditRequest> findAll();

    List<CreditRequest> findFiltered(Status status, LocalDate startDate, LocalDate endDate);

    void updateCreditRequestStatus(Long requestId, Status newStatus);
}