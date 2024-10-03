package com.smartbank.repositories;

import com.smartbank.entities.CreditRequest;

import java.util.List;

public interface CreditRequestRepository {

    CreditRequest save(CreditRequest creditRequest);

    CreditRequest findById(Long id);

    CreditRequest update(CreditRequest creditRequest);

    void deleteById(Long id);

    List<CreditRequest> findAll();

}
