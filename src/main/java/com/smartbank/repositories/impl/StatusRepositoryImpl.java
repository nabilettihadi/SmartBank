package com.smartbank.repositories.impl;

import com.smartbank.entities.Status;
import com.smartbank.repositories.StatusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
@ApplicationScoped
public class StatusRepositoryImpl implements StatusRepository {

    @Inject
    private EntityManager em;

    @Override
    public Status findByName(String name) {
        try {
            return em.createQuery("SELECT s FROM Status s WHERE s.name = :name", Status.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Status save(Status status) {
        try {
            em.persist(status);
            return status;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save status", e);
        }
    }
}
