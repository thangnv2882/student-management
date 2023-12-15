package com.pmmnm.StudentManagement.application.service.impl;

import com.pmmnm.StudentManagement.application.constants.CommonConstant;
import com.pmmnm.StudentManagement.application.constants.MessageConstant;
import com.pmmnm.StudentManagement.application.constants.RoleConstant;
import com.pmmnm.StudentManagement.application.input.classroom.AddStudentToClassroomInput;
import com.pmmnm.StudentManagement.application.input.classroom.AddTeacherToClassroomInput;
import com.pmmnm.StudentManagement.application.input.classroom.CreateClassroomInput;
import com.pmmnm.StudentManagement.application.input.classroom.UpdateClassroomInput;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.repository.ClassroomRepository;
import com.pmmnm.StudentManagement.application.repository.UserClassroomRepository;
import com.pmmnm.StudentManagement.application.repository.UserRepository;
import com.pmmnm.StudentManagement.application.service.IClassroomService;
import com.pmmnm.StudentManagement.config.exception.NotFoundException;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import com.pmmnm.StudentManagement.domain.entity.User;
import com.pmmnm.StudentManagement.domain.entity.UserClassroom;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pmmnm.StudentManagement.application.service.impl.UserServiceImpl.checkUserExists;

@Service
public class ClassroomServiceImpl implements IClassroomService {

    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;
    private final UserClassroomRepository userClassroomRepository;
    private final ModelMapper modelMapper;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository, UserRepository userRepository, UserClassroomRepository userClassroomRepository, ModelMapper modelMapper) {
        this.classroomRepository = classroomRepository;
        this.userRepository = userRepository;
        this.userClassroomRepository = userClassroomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom findClassroomById(Input input) {
        Classroom classroom = classroomRepository.findById(input.getId());
        checkClassroomExists(classroom);
        return classroom;
    }

    @Override
    public Output createClassroom(CreateClassroomInput input) {
        Classroom classroom = modelMapper.map(input, Classroom.class);
        classroomRepository.save(classroom);
        return new Output(CommonConstant.TRUE, CommonConstant.CREATED);
    }

    @Override
    public Output updateClassroom(UpdateClassroomInput input) {
        Classroom classroom = classroomRepository.findById(input.getId());
        checkClassroomExists(classroom);
        modelMapper.map(input, classroom);
        classroomRepository.save(classroom);
        return new Output(CommonConstant.TRUE, CommonConstant.UPDATED);
    }

    @Override
    public Output deleteClassroom(Input input) {
        Classroom classroom = classroomRepository.findById(input.getId());
        checkClassroomExists(classroom);
        classroomRepository.delete(classroom);
        return new Output(CommonConstant.TRUE, CommonConstant.DELETED);
    }

    @Override
    public Output addStudentToClassroom(AddStudentToClassroomInput input) {
        String idClassroom = input.getIdClassroom();
        String idUser = input.getIdUser();

        Classroom classroom = classroomRepository.findById(idClassroom);
        checkClassroomExists(classroom);

        User user = userRepository.findById(idUser);
        checkUserExists(user);
        boolean isUserAlreadyInClass = userClassroomRepository.isUserAlreadyClass(idClassroom, idUser);
        if (isUserAlreadyInClass) {
            return new Output(CommonConstant.TRUE, MessageConstant.STUDENT_ALREADY_IN_CLASS);
        }

        if (user.getRole().equals(RoleConstant.STUDENT)) {
            UserClassroom userClassroom = new UserClassroom(idClassroom, idUser);
            userClassroomRepository.save(userClassroom);
            return new Output(CommonConstant.TRUE, MessageConstant.ADD_STUDENT_TO_CLASSROOM_SUCCESS);
        }
        return new Output(CommonConstant.TRUE, MessageConstant.NO_STUDENT_ADDED_TO_CLASSROOM);
    }

    @Override
    public Output addTeacherToClassroom(AddTeacherToClassroomInput input) {
        Classroom classroom = classroomRepository.findById(input.getIdClassroom());
        checkClassroomExists(classroom);
        User user = userRepository.findById(input.getIdTeacher());
        if (user != null && user.getRole().equals(RoleConstant.TEACHER)) {
            classroom.setTeacherId(user.getId());
            classroomRepository.save(classroom);
            return new Output(CommonConstant.TRUE, MessageConstant.ADD_TEACHER_TO_CLASSROOM_SUCCESS);
        }
        throw new NotFoundException(MessageConstant.TEACHER_NOT_EXISTS);
    }

    @Override
    public List<User> getListStudentInClass(String idClassroom) {
        return userClassroomRepository.getListStudentInClass(idClassroom);
    }


    public static void checkClassroomExists(Classroom classroom) {
        if (classroom == null) {
            throw new NotFoundException(MessageConstant.CLASSROOM_NOT_EXISTS);
        }
    }

}
