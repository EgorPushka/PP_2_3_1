package web.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import web.models.User;


@Repository
public class UserDAOimpl implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<User> indexUsers() {
        return manager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        manager.persist(user);
    }

    @Override
    public void delete(User user) {
        manager.remove(manager.contains(user) ? user : manager.merge(user));

    }

    @Override
    public void edit(User user) {
        manager.merge(user);
    }

    @Override
    public User getById(int id) {
        return manager.find(User.class, id);
    }
}
