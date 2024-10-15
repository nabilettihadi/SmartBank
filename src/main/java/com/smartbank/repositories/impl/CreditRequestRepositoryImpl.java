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
import java.util.logging.Logger;

@ApplicationScoped
public class CreditRequestRepositoryImpl implements CreditRequestRepository {

    private static final Logger LOGGER = Logger.getLogger(CreditRequestRepositoryImpl.class.getName());

    @Inject
    private EntityManager em;

    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        try {
            em.getTransaction().begin();
            em.persist(creditRequest);
            em.getTransaction().commit();
            return creditRequest;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CreditRequest findById(Long id) {
        return em.find(CreditRequest.class, id);
    }

    @Override
    public CreditRequest update(CreditRequest creditRequest) {
        try {
            em.getTransaction().begin();
            CreditRequest updatedRequest = em.merge(creditRequest);
            em.getTransaction().commit();
            return updatedRequest;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e.getMessage());
        }
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
        return em.createQuery("SELECT cr FROM CreditRequest cr", CreditRequest.class).getResultList();
    }

    @Override
    public List<CreditRequest> findFiltered(Status status, LocalDate startDate, LocalDate endDate) {
        StringBuilder queryString = new StringBuilder("SELECT cr FROM CreditRequest cr JOIN cr.histories h WHERE h.id = (SELECT MAX(h2.id) FROM History h2 WHERE h2.creditRequest = cr)");

        if (status != null) {
            queryString.append(" AND h.status = :status");
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
}