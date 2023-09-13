package web.dao;

import web.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDAOimpl implements UserDAO {
    //временная имитация БД
    private static final AtomicInteger AtomID = new AtomicInteger(0);
    private static Map<Integer, User> tmpDS = new HashMap<>();

    static {
        User user1 = new User();
        user1.setId(AtomID.getAndIncrement());
        user1.setName("user1");
        user1.setAge(10);
        tmpDS.put(user1.getId(), user1);

        User user2 = new User();
        user2.setId(AtomID.getAndIncrement());
        user2.setName("user3");
        user2.setAge(20);
        tmpDS.put(user2.getId(), user2);

        User user3 = new User();
        user3.setId(AtomID.getAndIncrement());
        user3.setName("user3");
        user3.setAge(30);
        tmpDS.put(user3.getId(), user3);
    }


    @Override
    public List<User> indexUsers() {
        return new ArrayList<>(tmpDS.values());
    }

    @Override
    public void add(User user) {
        user.setId(AtomID.getAndIncrement());
        tmpDS.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        tmpDS.remove(user.getId());
    }

    @Override
    public void edit(User user) {
        tmpDS.put(user.getId(), user);
    }

    @Override
    public User getById(int id) {
        return tmpDS.get(id);
    }
}