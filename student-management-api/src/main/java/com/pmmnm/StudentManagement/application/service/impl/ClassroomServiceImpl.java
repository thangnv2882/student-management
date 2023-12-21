package com.pmmnm.StudentManagement.application.service.impl;

import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.application.constants.CommonConstant;
import com.pmmnm.StudentManagement.application.constants.MessageConstant;
import com.pmmnm.StudentManagement.application.input.classroom.CreateClassroomInput;
import com.pmmnm.StudentManagement.application.input.classroom.UpdateClassroomInput;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.output.classroom.DetailClassroomOutput;
import com.pmmnm.StudentManagement.application.output.classroom.UserScoreOutput;
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

import java.util.ArrayList;
import java.util.List;

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
    public DetailClassroomOutput getDetailClassroom(Input input) {
        Classroom classroom = classroomRepository.findById(input.getId());
        checkClassroomExists(classroom);
        DetailClassroomOutput detailClassroomOutput = modelMapper.map(classroom, DetailClassroomOutput.class);
        List<UserScoreOutput> userScoreOutputs = new ArrayList<>();
        List<UserClassroom> userClassrooms = userClassroomRepository.findByIdClassroom(input.getId());
        for (UserClassroom userClassroom : userClassrooms) {
            User user = userRepository.findById(userClassroom.getIdUser());
            UserScoreOutput userScoreOutput = new UserScoreOutput(user.getId(), user.getName(), userClassroom.getScore());
            userScoreOutputs.add(userScoreOutput);
        }
        detailClassroomOutput.setUserScoreOutputList(userScoreOutputs);
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
        // Remove classroom
        checkClassroomExists(classroom);
        classroomRepository.delete(classroom);

        // Remove user classroom
        UserClassroom userClassroom = new UserClassroom();
        userClassroom.setIdClassroom(input.getId());
        ObjectSet<UserClassroom> userClassrooms = userClassroomRepository.findByExample(userClassroom);
        System.out.println("userClassrooms: "+ userClassrooms);
        while (userClassrooms.hasNext()) {
            userClassroomRepository.delete(userClassrooms.next());
        }
        return new Output(CommonConstant.TRUE, CommonConstant.DELETED);
    }

    @Override
    public List<User> getListStudentInClass(String idClassroom) {
        UserClassroom userClassroom = new UserClassroom();
        userClassroom.setIdClassroom(idClassroom);
        ObjectSet<UserClassroom> result = userClassroomRepository.findByExample(userClassroom);
        List<User> users = new ArrayList<>();
        while (result.hasNext()) {
            users.add(userRepository.findById(result.next().getIdUser()));
        }
        return users;
    }


    public static void checkClassroomExists(Classroom classroom) {
        if (classroom == null) {
            throw new NotFoundException(MessageConstant.CLASSROOM_NOT_EXISTS);
        }
    }

}
