package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
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
//        Query By Example
        System.out.println("Query By Example");
        User user = new User(id);
        ObjectSet<User> result = db.queryByExample(user);
        return result.hasNext() ? result.next() : null;

//        Native query
//        System.out.println("Native query");
//        List<User> userClassrooms = db.query(new Predicate<User>() {
//            public boolean match(User user) {
//                return user.getId().equals(id);
//            }
//        });
//        return userClassrooms.isEmpty() ? null : userClassrooms.get(0);

//        SODA query
//        System.out.println("SODA query");
//        Query query = db.query();
//        query.constrain(User.class);
//        query.descend("id").constrain(id);
//        ObjectSet<User> result = query.execute();
//        return result.hasNext() ? result.next() : null;
    }
}
