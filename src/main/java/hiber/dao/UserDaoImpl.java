package hiber.dao;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private EntityManager em;

    @Override
    public List<User> allUsers() {
        return em.createQuery("from User")
                .getResultList();
    }

    @Override
    public void add(User user) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            if (user.getId() == null) {
                em.persist(user);
            } else {
                user = em.merge(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            User user = em.find(User.class, id);
            em.remove(em.contains(user) ? user : em.merge(user));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void edit(User user) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User find(Long id) {
        return em.find(User.class, id);
    }
}
