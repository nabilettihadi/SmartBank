package com.smartbank.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManagerFactoryUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            synchronized (EntityManagerFactoryUtil.class) {
                if (entityManagerFactory == null) {
                    try {
                        entityManagerFactory = Persistence.createEntityManagerFactory("default");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
