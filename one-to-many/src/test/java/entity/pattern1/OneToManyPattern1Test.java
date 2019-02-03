package entity.pattern1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;

class OneToManyPattern1Test {

    @Test
    void test() {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        final EntityManager em = factory.createEntityManager();

        final Long classRoomId = insert(em);
        em.close();

        final EntityManager em2 = factory.createEntityManager();
        find(em2, classRoomId);
        em2.close();

    }

    private Long insert(final EntityManager em) {

        final Teacher1 teacher = new Teacher1();
        teacher.setId(1L);
        teacher.setName("横山先生");

        final Student1 inoue = new Student1();
        inoue.setId(11L);
        inoue.setName("井上");

        final Student1 sato = new Student1();
        sato.setId(12L);
        sato.setName("佐藤");

        final Set<Student1> students = new HashSet<>(Arrays.asList(inoue, sato));

        final ClassRoom1 classRoom = new ClassRoom1();
        classRoom.setId(101L);
        classRoom.setName("3年1組");

        classRoom.setHomeroomTeacher(teacher);

        classRoom.setStudents(students);

        final EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(teacher);
        em.persist(inoue);
        em.persist(sato);
        em.persist(classRoom);
        tx.commit();

        return classRoom.getId();
    }

    private void find(final EntityManager em, final Long classRoomId) {
        final ClassRoom1 room = em.find(ClassRoom1.class, classRoomId);

        assertEquals("3年1組", room.getName());

        final Teacher1 teacher = room.getHomeroomTeacher();
        assertEquals("横山先生", teacher.getName());

        final Set<Student1> students = room.getStudents();
        assertEquals(2, students.size());
        final Set<String> studentNames = students.stream().map(Student1::getName).collect(Collectors.toSet());
        assertTrue(studentNames.contains("井上"));
        assertTrue(studentNames.contains("佐藤"));
    }

}
