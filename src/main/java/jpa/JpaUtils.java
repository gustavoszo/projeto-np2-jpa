package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("faculdade");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

}
