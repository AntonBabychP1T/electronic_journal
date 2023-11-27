package electronic.journal.repository.impl;

import electronic.journal.repository.StudentDao;
import electronic.journal.model.Group;
import electronic.journal.model.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Student addStudent(Student student) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save Student " + student + " to DB");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return student;
    }

    @Override
    public Optional<Student> getStudentByLastName(String lastName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> studentQuery = session.createQuery(
                    "FROM Student s WHERE s.lastName = :lastName ", Student.class);
            studentQuery.setParameter("lastName", lastName);
            return studentQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException("Can't get student by last name: " + lastName, e);
        }
    }

    @Override
    public List<Student> getAllStudent() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);
            Root<Student> studentRoot = criteriaQuery.from(Student.class);
            studentRoot.fetch("group", JoinType.LEFT).fetch("subjects", JoinType.LEFT);
            criteriaQuery.select(studentRoot);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all students", e);
        }
    }

    @Override
    public List<Student> getAllStudentByGroup(Group group) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> studentQuery = session.createQuery(
                    "FROM Student s LEFT JOIN FETCH s.group g LEFT JOIN FETCH g.subjects sub WHERE g.name = :groupName ", Student.class);
            studentQuery.setParameter("groupName", group.getName());
            return studentQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all student by group: " + group, e);
        }
    }
}
