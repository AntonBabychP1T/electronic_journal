package electronic.journal.repository.impl;

import electronic.journal.repository.GroupDao;
import electronic.journal.model.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDaoImpl implements GroupDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public GroupDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Group addGroup(Group group) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(group);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save Group " + group + " to DB");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return group;
    }
}
