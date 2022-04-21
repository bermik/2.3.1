package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Transactional
    @Override
    public User find(Long id) { return userDao.find(id); }
}
