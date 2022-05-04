package hiber.dao;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Component
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> allUsers() {
        return em.createQuery("from User")
                .getResultList();
    }

    @Override
    public void add(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            user = em.merge(user);
        }
    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public void edit(User user) {
        em.merge(user);
    }

    @Override
    public User find(Long id) {
        return em.find(User.class, id);
    }
}
