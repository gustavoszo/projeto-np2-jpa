package jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

}
