package com.smartbank.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryUtil {
    private EntityManagerFactoryUtil() {}

    private static final EntityManagerFactory em =
            Persistence.createEntityManagerFactory("default");

    @Produces
    public EntityManager getEntityManager() {
        return em.createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (em.isOpen()) {
            em.close();
        }
    }
}
