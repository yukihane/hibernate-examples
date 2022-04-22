package examples.jpa

import examples.jpa.entity.AltId
import examples.jpa.entity.Person
import org.hibernate.SessionFactory
import org.hibernate.criterion.Restrictions
import org.hibernate.testing.transaction.TransactionUtil.doInHibernate
import org.hibernate.testing.transaction.TransactionUtil.doInJPA
import org.junit.jupiter.api.Test
import javax.persistence.Persistence


class HelloTest {

    private val entityManagerFactory = Persistence.createEntityManagerFactory("example.unit")

    private val sessionFactory = entityManagerFactory.unwrap(SessionFactory::class.java)

    @Test
    fun testApp() {
        val altId = AltId(1, 1)

        doInJPA(this::entityManagerFactory) { em ->
            val p = Person(altId)
            em.persist(p)
        }

        doInHibernate<Unit>(this::sessionFactory) { session ->
            val c = session.createCriteria(Person::class.java)
            c.add(Restrictions.`in`("altId", listOf(altId, AltId(1, 2))))
            val result = c.list() as List<Person>
            result.forEach { println(it.id) }
        }
    }
}
