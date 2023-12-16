package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
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
    private final ClassroomRepository classroomRepository;

    public UserClassroomRepository(UserRepository userRepository, ClassroomRepository classroomRepository) {
        this.userRepository = userRepository;
        this.classroomRepository = classroomRepository;
    }

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

    public List<Classroom> getListClassOfUser(String idUser) {
        UserClassroom userClassroom = new UserClassroom();
        userClassroom.setIdUser(idUser);
        ObjectSet<UserClassroom> result = db.queryByExample(userClassroom);
        List<Classroom> classrooms = new ArrayList<>();
        while (result.hasNext()) {
            classrooms.add(classroomRepository.findById(result.next().getIdClassroom()));
        }
        return classrooms;
    }


    public List<UserClassroom> listUserClass(ObjectSet<UserClassroom> result) {
        List<UserClassroom> userClassroomList = new ArrayList<>();
        while (result.hasNext()) {
            userClassroomList.add(result.next());
        }
        return userClassroomList;
    }

}
