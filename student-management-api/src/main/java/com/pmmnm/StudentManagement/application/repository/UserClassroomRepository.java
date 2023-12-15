package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.domain.entity.User;
import com.pmmnm.StudentManagement.domain.entity.UserClassroom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.pmmnm.StudentManagement.application.utils.DB4OUtil.getObjectContainer;

@Repository
public class UserClassroomRepository {

    private final ObjectContainer db = getObjectContainer();

    private final UserRepository userRepository;

    public UserClassroomRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserClassroom userClassroom) {
        db.store(userClassroom);
    }

    public void delete(UserClassroom userClassroom) {
        db.delete(userClassroom);
    }

    public List<UserClassroom> findAll() {
        List<UserClassroom> userClassroomList = new ArrayList<>();
        ObjectSet<UserClassroom> result = db.query(UserClassroom.class);
        return listUserClass(result);
    }

    public boolean isUserAlreadyClass(String idClassRoom, String idUser) {
        UserClassroom userClassroom = new UserClassroom(idClassRoom, idUser);
        ObjectSet<User> result = db.queryByExample(userClassroom);
        return result.hasNext();
    }

    public List<User> getListStudentInClass(String idClassroom) {
        UserClassroom userClassroom = new UserClassroom();
        userClassroom.setIdClassroom(idClassroom);
        ObjectSet<UserClassroom> result = db.queryByExample(userClassroom);
        List<User> users = new ArrayList<>();
        while (result.hasNext()) {
            users.add(userRepository.findById(result.next().getIdUser()));
        }
        return users;
    }

    public List<UserClassroom> getListClassOfUser(String idUser) {
        UserClassroom userClassroom = new UserClassroom();
        userClassroom.setIdUser(idUser);
        ObjectSet<UserClassroom> result = db.queryByExample(userClassroom);
        return listUserClass(result);
    }


    public List<UserClassroom> listUserClass(ObjectSet<UserClassroom> result) {
        List<UserClassroom> userClassroomList = new ArrayList<>();
        while (result.hasNext()) {
            userClassroomList.add(result.next());
        }
        return userClassroomList;
    }

}
