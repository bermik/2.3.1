package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void add(User user);
    void delete(Long id);
    void edit(User user);
    User find(Long id);
}
