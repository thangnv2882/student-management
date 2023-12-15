package com.pmmnm.StudentManagement.application.service;

import com.pmmnm.StudentManagement.application.input.student.CreateStudentInput;
import com.pmmnm.StudentManagement.application.input.student.UpdateStudentInput;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.domain.entity.User;

import java.util.List;

public interface IStudentService {

    List<User> findAll();

    User findStudentById(Input input);

    Output createStudent(CreateStudentInput input);

    Output updateStudent(UpdateStudentInput input);

    Output deleteStudent(Input input);

}
