package basic;

import entity.ClassRoom;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;

class PersistTest {

    @Test
    void test() {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        final EntityManager em = factory.createEntityManager();

        em.persist(new ClassRoom());
    }

}
