package com.smartbank.repositories.impl;

import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.Status;
import com.smartbank.repositories.CreditRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
@ApplicationScoped
public class CreditRequestRepositoryImpl implements CreditRequestRepository {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public CreditRequest save(CreditRequest creditRequest) {
        em.persist(creditRequest);
        return creditRequest;
    }

    @Override
    public CreditRequest findById(Long id) {
        return em.find(CreditRequest.class, id);
    }

    @Override
    @Transactional
    public CreditRequest update(CreditRequest creditRequest) {
        return em.merge(creditRequest);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        CreditRequest creditRequest = em.find(CreditRequest.class, id);
        if (creditRequest != null) {
            em.remove(creditRequest);
            return true;
        }
        return false;
    }

    @Override
    public List<CreditRequest> findAll() {
        return em.createQuery("FROM CreditRequest", CreditRequest.class).getResultList();
    }

    @Override
    public List<CreditRequest> findFiltered(Status status, LocalDate startDate, LocalDate endDate) {
        StringBuilder queryString = new StringBuilder("SELECT cr FROM CreditRequest cr WHERE 1=1");

        if (status != null) {
            queryString.append(" AND cr.Status = :status");
        }
        if (startDate != null) {
            queryString.append(" AND cr.createdAt >= :startDate");
        }
        if (endDate != null) {
            queryString.append(" AND cr.createdAt <= :endDate");
        }

        TypedQuery<CreditRequest> query = em.createQuery(queryString.toString(), CreditRequest.class);

        if (status != null) {
            query.setParameter("status", status);
        }
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }

        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateCreditRequestStatus(Long requestId, Status newStatus) {
        CreditRequest creditRequest = em.find(CreditRequest.class, requestId);
        if (creditRequest != null) {
            creditRequest.setStatus(newStatus);
            creditRequest.setUpdatedAt(LocalDate.now());
            em.merge(creditRequest);
        } else {
            throw new IllegalArgumentException("Credit request with ID " + requestId + " not found.");
        }
    }
}
