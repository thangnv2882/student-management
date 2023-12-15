package com.pmmnm.StudentManagement.application.service.impl;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.application.constants.CommonConstant;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.input.student.CreateStudentInput;
import com.pmmnm.StudentManagement.application.input.student.UpdateStudentInput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.service.IStudentService;
import com.pmmnm.StudentManagement.domain.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private static ObjectContainer db;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "student-management-db.db4o");
        ObjectSet<User> result = db.query(User.class);
        while (result.hasNext()) {
            users.add(result.next());
        }
        db.close();
        return users;
    }

    @Override
    public User findStudentById(Input input) {
        return null;
    }

    @Override
    public Output createStudent(CreateStudentInput input) {
        User user = modelMapper.map(input, User.class);
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "student-management-db.db4o");
        db.store(user);
        db.close();
        return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
    }


    @Override
    public Output deleteStudent(Input input) {
        return null;
    }

    @Override
    public Output updateStudent(UpdateStudentInput input) {
        return null;
    }

}
