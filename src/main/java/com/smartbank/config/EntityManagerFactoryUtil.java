package com.smartbank.config;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryUtil {
    private static final EntityManagerFactory emFactory =
            Persistence.createEntityManagerFactory("default");

    @Produces
    @ApplicationScoped
    public EntityManager createEntityManager() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("default");
        return emf.createEntityManager();
    }

    @PreDestroy
    public void closeEntityManagerFactory() {
        if (emFactory.isOpen()) {
            emFactory.close();
        }
    }
}