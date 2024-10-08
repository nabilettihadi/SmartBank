package com.smartbank.repositories.impl;

import com.smartbank.config.EntityManagerFactoryUtil;
import com.smartbank.entities.CreditRequest;
import com.smartbank.entities.CreditRequestStatus;
import com.smartbank.repositories.CreditRequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class CreditRequestRepositoryImpl implements CreditRequestRepository {

    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(creditRequest);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return creditRequest;
    }

    @Override
    public CreditRequest findById(Long id) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        try {
            return em.find(CreditRequest.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public CreditRequest update(CreditRequest creditRequest) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            CreditRequest updatedCreditRequest = em.merge(creditRequest);
            em.getTransaction().commit();
            return updatedCreditRequest;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        try {
            CreditRequest creditRequest = em.find(CreditRequest.class, id);
            if (creditRequest != null) {
                em.getTransaction().begin();
                em.remove(creditRequest);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public List<CreditRequest> findAll() {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        try {
            return em.createQuery("FROM CreditRequest", CreditRequest.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<CreditRequest> findFiltered(CreditRequestStatus status, LocalDate startDate, LocalDate endDate) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        StringBuilder queryString = new StringBuilder("SELECT cr FROM CreditRequest cr WHERE 1=1");

        if (status != null) {
            queryString.append(" AND cr.status = :status");
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
