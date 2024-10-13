package com.smartbank.repositories.impl;

import com.smartbank.entities.Status;
import com.smartbank.repositories.StatusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class StatusRepositoryImpl implements StatusRepository {

    @Inject
    private EntityManager em;

    @Override
    public Status findById(Long id) {
        return em.find(Status.class, id);
    }

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
        em.persist(status);
        return status;
    }

    @Override
    public List<Status> findAll() {
        return em.createQuery("SELECT s FROM Status s", Status.class)
                .getResultList();
    }
}