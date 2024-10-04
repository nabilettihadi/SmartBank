package com.smartbank.repositories.impl;

import com.smartbank.config.EntityManagerFactoryUtil;
import com.smartbank.entities.CreditRequest;
import com.smartbank.repositories.CreditRequestRepository;
import jakarta.persistence.EntityManager;

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

}
