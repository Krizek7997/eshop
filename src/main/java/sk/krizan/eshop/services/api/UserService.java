package sk.krizan.eshop.services.api;

import sk.krizan.eshop.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getById(Integer id);
    Integer add(User user);
    void delete(Integer id);
    void update(Integer id, User user);

}
