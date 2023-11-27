package electronic.journal.repository.impl;

import electronic.journal.model.Grade;
import electronic.journal.model.Student;
import electronic.journal.model.Subject;
import electronic.journal.repository.GradeDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GradeDaoImpl implements GradeDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public GradeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Grade addGrade(Grade grade) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(grade);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save grade " + grade + " to DB");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return grade;
    }

    @Override
    public Optional<Grade> getGradeByStudentAndSubject(Student student, Subject subject) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Grade> criteriaQuery = criteriaBuilder.createQuery(Grade.class);
            Root<Grade> gradeRoot = criteriaQuery.from(Grade.class);
            Predicate studentPredicate = criteriaBuilder.equal(gradeRoot.get("student"), student);
            Predicate subjectPredicate = criteriaBuilder.equal(gradeRoot.get("subject"), subject);
            criteriaQuery.where(criteriaBuilder.and(studentPredicate, subjectPredicate));
            return session.createQuery(criteriaQuery).uniqueResultOptional();
        }
    }

    @Override
    public List<Grade> getAllGradeForStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Grade> criteriaQuery = criteriaBuilder.createQuery(Grade.class);
            Root<Grade> gradeRoot = criteriaQuery.from(Grade.class);
            Predicate studentPredicate = criteriaBuilder.equal(gradeRoot.get("student"), student);
            criteriaQuery.where(studentPredicate);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public List<Grade> getAllGradeForSubject(Subject subject) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Grade> criteriaQuery = criteriaBuilder.createQuery(Grade.class);
            Root<Grade> gradeRoot = criteriaQuery.from(Grade.class);
            Predicate studentPredicate = criteriaBuilder.equal(gradeRoot.get("subject"), subject);
            criteriaQuery.where(studentPredicate);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
