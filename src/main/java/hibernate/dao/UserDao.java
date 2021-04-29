package hibernate.dao;

import hibernate.model.User;


import java.util.List;

public interface UserDao{
    void add(User user);
    void remove(Long id);
    void update(User user,Long id);
    List<User> listUsers();
    User findById(Long id);

}
