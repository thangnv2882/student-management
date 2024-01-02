package com.pmmnm.StudentManagement.application.service.impl;

import com.pmmnm.StudentManagement.application.constants.CommonConstant;
import com.pmmnm.StudentManagement.application.constants.MessageConstant;
import com.pmmnm.StudentManagement.application.constants.RoleConstant;
import com.pmmnm.StudentManagement.application.input.userClassroom.*;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.repository.ClassroomRepository;
import com.pmmnm.StudentManagement.application.repository.UserClassroomRepository;
import com.pmmnm.StudentManagement.application.repository.UserRepository;
import com.pmmnm.StudentManagement.application.service.IUserClassroomService;
import com.pmmnm.StudentManagement.config.exception.NotFoundException;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import com.pmmnm.StudentManagement.domain.entity.User;
import com.pmmnm.StudentManagement.domain.entity.UserClassroom;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static com.pmmnm.StudentManagement.application.service.impl.ClassroomServiceImpl.checkClassroomExists;
import static com.pmmnm.StudentManagement.application.service.impl.UserServiceImpl.checkUserExists;

@Service
public class UserClassroomServiceImpl implements IUserClassroomService {

    private final UserClassroomRepository userClassroomRepository;
    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;

    public UserClassroomServiceImpl(UserClassroomRepository userClassroomRepository, ClassroomRepository classroomRepository, UserRepository userRepository) {
        this.userClassroomRepository = userClassroomRepository;
        this.classroomRepository = classroomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Output addStudentToClassroom(AddStudentToClassroomInput input) {
        String idClassroom = input.getIdClassroom();
        String idUser = input.getIdUser();

        Classroom classroom = classroomRepository.findById(idClassroom);
        checkClassroomExists(classroom);

        User user = userRepository.findById(idUser);
        checkUserExists(user);
        UserClassroom userClassroom = userClassroomRepository.findById(idClassroom, idUser);
        if (userClassroom != null) {
            return new Output(CommonConstant.TRUE, MessageConstant.STUDENT_ALREADY_IN_CLASS);
        }

        if (user.getRole().equals(RoleConstant.STUDENT)) {
            userClassroom = new UserClassroom(idClassroom, idUser);
            userClassroomRepository.save(userClassroom);
            return new Output(CommonConstant.TRUE, MessageConstant.ADD_STUDENT_TO_CLASSROOM_SUCCESS);
        }
        return new Output(CommonConstant.TRUE, MessageConstant.NO_STUDENT_ADDED_TO_CLASSROOM);
    }

    @Override
    public Output removeStudentFromClassroom(RemoveStudentFromClassroomInput input) {
        String idClassroom = input.getIdClassroom();
        String idUser = input.getIdUser();

        Classroom classroom = classroomRepository.findById(idClassroom);
        checkClassroomExists(classroom);

        User user = userRepository.findById(idUser);
        checkUserExists(user);
        UserClassroom userClassroom = userClassroomRepository.findById(idClassroom, idUser);
        if (userClassroom == null) {
            return new Output(CommonConstant.TRUE, MessageConstant.STUDENT_NOT_IN_CLASS);
        }

        userClassroomRepository.delete(userClassroom);
        return new Output(CommonConstant.TRUE, MessageConstant.REMOVE_STUDENT_TO_CLASSROOM_SUCCESS);
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
    public Output enterScore(EnterScoreInput input) {
        UserClassroom userClassroom = userClassroomRepository.findById(input.getIdClassroom(), input.getIdStudent());
        if (userClassroom == null) {
            System.out.println("Student: " + input.getIdStudent() + " not in class " + input.getIdClassroom());
        } else {
            userClassroom.setScore(input.getScore());
            userClassroomRepository.save(userClassroom);
        }
        return new Output(CommonConstant.TRUE, CommonConstant.SUCCESS);
    }

    @Override
    public Output importScoreFromCSV(ImportScoreFromCSVInput input) {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(input.getFile().getInputStream(), StandardCharsets.UTF_8));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String idStudent = values[0];
                Double score;
                if (values.length == 3) {
                    score = Double.valueOf(values[2]);
                } else {
                    score = null;
                }
                EnterScoreInput enterScoreInput = new EnterScoreInput(input.getIdClassroom(), idStudent, score);
                enterScore(enterScoreInput);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Output(CommonConstant.TRUE, MessageConstant.IMPORT_SCORE_SUCCESSFULLY);
    }

}
