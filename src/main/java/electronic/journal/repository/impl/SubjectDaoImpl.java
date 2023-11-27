package electronic.journal.repository.impl;


import electronic.journal.repository.SubjectDao;
import electronic.journal.model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDaoImpl implements SubjectDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public SubjectDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Subject addSubject(Subject subject) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(subject);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save subject " + subject + " to DB");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return subject;
    }
}
