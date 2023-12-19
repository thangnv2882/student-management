package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import com.pmmnm.StudentManagement.domain.entity.User;
import com.pmmnm.StudentManagement.domain.entity.UserClassroom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.pmmnm.StudentManagement.application.service.impl.ClassroomServiceImpl.checkClassroomExists;
import static com.pmmnm.StudentManagement.application.service.impl.UserServiceImpl.checkUserExists;
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
        Classroom classroom = classroomRepository.findById(idClassroom);
        checkClassroomExists(classroom);
        List<User> users = new ArrayList<>();
//        if (userClassrooms != null) {
        for (UserClassroom userClassroom : classroom.getUserClassrooms()) {
            users.add(userRepository.findById(userClassroom.getIdUser()));
        }
//        }
        return users;
    }

    public List<Classroom> getListClassOfUser(String idUser) {
        User user = userRepository.findById(idUser);
        checkUserExists(user);
        List<Classroom> classrooms = new ArrayList<>();
        for (UserClassroom userClassroom : user.getUserClassrooms()) {
            classrooms.add(classroomRepository.findById(userClassroom.getIdClassroom()));
        }
        return classrooms;
    }

}
