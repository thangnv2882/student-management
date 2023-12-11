package com.pmmnm.StudentManagement.application.service.impl;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.pmmnm.StudentManagement.application.constants.CommonConstant;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.input.student.CreateStudentInput;
import com.pmmnm.StudentManagement.application.input.student.UpdateStudentInput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.service.IStudentService;
import com.pmmnm.StudentManagement.domain.entity.Student;
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
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "test2.db4o");
        ObjectSet<Student> result = db.query(Student.class);
        while (result.hasNext()) {
            students.add(result.next());
        }
        db.close();
        return students;
    }

    @Override
    public Student findStudentById(Input input) {
        return null;
    }

    @Override
    public Output createStudent(CreateStudentInput input) {
        Student student = modelMapper.map(input, Student.class);
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "test2.db4o");
        db.store(student);
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
