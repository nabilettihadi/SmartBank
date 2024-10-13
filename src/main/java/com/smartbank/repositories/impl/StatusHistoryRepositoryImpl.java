package com.smartbank.repositories.impl;

import com.smartbank.entities.History;
import com.smartbank.repositories.StatusHistoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

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
}