package web.dao;

import web.model.User;
import java.util.List;

public interface UserDAO {
    List<User> indexUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id);
}
