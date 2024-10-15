package com.smartbank.repositories.impl;

import com.smartbank.entities.History;
import com.smartbank.repositories.StatusHistoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class StatusHistoryRepositoryImpl implements StatusHistoryRepository {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public History save(History statusHistory) {
        try {
            em.persist(statusHistory);
            return statusHistory;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save status history", e);
        }
    }

    @Override
    public List<History> findByCreditRequestId(Long creditRequestId) {
        return em.createQuery("SELECT h FROM History h WHERE h.creditRequest.id = :creditRequestId ORDER BY h.changeDate", History.class)
                .setParameter("creditRequestId", creditRequestId)
                .getResultList();
    }
}