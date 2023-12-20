package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.pmmnm.StudentManagement.application.utils.DB4OUtil.getObjectContainer;

@Repository
public class UserRepository {

    private final ObjectContainer db = getObjectContainer();

    public void save(Object user) {
        db.store(user);
    }

    public void delete(User user) {
        db.delete(user);
    }

    public User login(String id, String password) {
        User user = new User(id, password);
        ObjectSet<User> result = db.queryByExample(user);
        return result.hasNext() ? result.next() : null;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        ObjectSet<User> result = db.query(User.class);
        while (result.hasNext()) {
            users.add(result.next());
        }
        return users;
    }

    public User findById(String id) {
        User user = new User(id);
        ObjectSet<User> result = db.queryByExample(user);
        return result.hasNext() ? result.next() : null;
    }
}
