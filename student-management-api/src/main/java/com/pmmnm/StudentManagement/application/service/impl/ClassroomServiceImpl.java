package com.pmmnm.StudentManagement.application.service.impl;

import com.pmmnm.StudentManagement.application.constants.CommonConstant;
import com.pmmnm.StudentManagement.application.constants.MessageConstant;
import com.pmmnm.StudentManagement.application.constants.RoleConstant;
import com.pmmnm.StudentManagement.application.input.classroom.*;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.input.user.EnterScoreInput;
import com.pmmnm.StudentManagement.application.output.classroom.DetailClassroomOutput;
import com.pmmnm.StudentManagement.application.output.classroom.UserPointOutput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.repository.ClassroomRepository;
import com.pmmnm.StudentManagement.application.repository.UserClassroomRepository;
import com.pmmnm.StudentManagement.application.repository.UserRepository;
import com.pmmnm.StudentManagement.application.service.IClassroomService;
import com.pmmnm.StudentManagement.application.service.IUserService;
import com.pmmnm.StudentManagement.config.exception.NotFoundException;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import com.pmmnm.StudentManagement.domain.entity.User;
import com.pmmnm.StudentManagement.domain.entity.UserClassroom;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.pmmnm.StudentManagement.application.service.impl.UserServiceImpl.checkUserExists;

@Service
public class ClassroomServiceImpl implements IClassroomService {

    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;
    private final UserClassroomRepository userClassroomRepository;
    private final IUserService userService;
    private final ModelMapper modelMapper;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository, UserRepository userRepository, UserClassroomRepository userClassroomRepository, IUserService userService, ModelMapper modelMapper) {
        this.classroomRepository = classroomRepository;
        this.userRepository = userRepository;
        this.userClassroomRepository = userClassroomRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public DetailClassroomOutput getDetailClassroom(Input input) {
        Classroom classroom = classroomRepository.findById(input.getId());
        checkClassroomExists(classroom);
        DetailClassroomOutput detailClassroomOutput = modelMapper.map(classroom, DetailClassroomOutput.class);
        List<UserPointOutput> userPointOutputs = new ArrayList<>();
        List<UserClassroom> userClassrooms = classroom.getUserClassrooms();
        for (UserClassroom userClassroom : userClassrooms) {
            User user = userRepository.findById(userClassroom.getIdUser());
            UserPointOutput userPointOutput = new UserPointOutput(user.getId(), user.getName(), userClassroom.getScore());
            userPointOutputs.add(userPointOutput);
        }
        detailClassroomOutput.setUserPointOutputList(userPointOutputs);
        return detailClassroomOutput;
    }

    @Override
    public Output createClassroom(CreateClassroomInput input) {
        Classroom classroom = classroomRepository.findById(input.getId());
        if (classroom != null) {
            return new Output(CommonConstant.TRUE, MessageConstant.CLASSROOM_ALREADY_EXISTS);
        }
        classroom = modelMapper.map(input, Classroom.class);
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
        UserClassroom userClassroom = userClassroomRepository.findById(idClassroom, idUser);
        if (userClassroom != null) {
            return new Output(CommonConstant.TRUE, MessageConstant.STUDENT_ALREADY_IN_CLASS);
        }

        if (user.getRole().equals(RoleConstant.STUDENT)) {
            userClassroom = new UserClassroom(idClassroom, idUser);
            userClassroomRepository.save(userClassroom);

            List<UserClassroom> userClassroomsOfUser = user.getUserClassrooms();
            userClassroomsOfUser.add(userClassroom);
            user.setUserClassrooms(userClassroomsOfUser);
            userRepository.save(user);


            List<UserClassroom> userClassroomsOfClass = classroom.getUserClassrooms();
            userClassroomsOfClass.add(userClassroom);
            classroom.setUserClassrooms(userClassroomsOfClass);
            classroomRepository.save(classroom);

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

    @Override
    public Output importScoreFromCSV(ImportScoreFromCSVInput input) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(input.getFile().getInputStream(), StandardCharsets.UTF_8));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String idStudent = values[0];
                Integer score;
                if (values.length == 2) {
                    score = Integer.valueOf(values[1]);
                } else {
                    score = null;
                }
                EnterScoreInput enterScoreInput = new EnterScoreInput(input.getIdClassroom(), idStudent, score);
                userService.enterScore(enterScoreInput);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Output(CommonConstant.TRUE, MessageConstant.IMPORT_SCORE_SUCCESSFULLY);
    }


    public static void checkClassroomExists(Classroom classroom) {
        if (classroom == null) {
            throw new NotFoundException(MessageConstant.CLASSROOM_NOT_EXISTS);
        }
    }

}
