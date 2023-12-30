package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import com.pmmnm.StudentManagement.domain.entity.UserClassroom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.pmmnm.StudentManagement.application.utils.DB4OUtil.getObjectContainer;

@Repository
public class UserClassroomRepository {

    private final ObjectContainer db = getObjectContainer();

    public void save(UserClassroom userClassroom) {
        db.store(userClassroom);
    }

    public void delete(UserClassroom userClassroom) {
        db.delete(userClassroom);
    }


    public UserClassroom findById(String idClassroom, String idUser) {
        UserClassroom userClassroom = new UserClassroom(idClassroom, idUser);
        ObjectSet<UserClassroom> result = db.queryByExample(userClassroom);
        return result.hasNext() ? result.next() : null;
    }


    public List<UserClassroom> findByIdClassroom(String idClassroom) {
        UserClassroom userClassroom = new UserClassroom();
        userClassroom.setIdClassroom(idClassroom);
        ObjectSet<UserClassroom> result = db.queryByExample(userClassroom);
        List<UserClassroom> userClassrooms = new ArrayList<>();
        while (result.hasNext()) {
            userClassrooms.add(result.next());
        }
        return userClassrooms;
    }

    public ObjectSet<UserClassroom> findByExample(UserClassroom userClassroom) {
        return db.queryByExample(userClassroom);
    }

}
