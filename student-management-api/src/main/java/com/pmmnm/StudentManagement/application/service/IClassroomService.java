package com.pmmnm.StudentManagement.application.service;

import com.pmmnm.StudentManagement.application.input.classroom.*;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.output.classroom.DetailClassroomOutput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import com.pmmnm.StudentManagement.domain.entity.User;

import java.util.List;

public interface IClassroomService {

    List<Classroom> findAll();

    DetailClassroomOutput getDetailClassroom(Input input);

    Output createClassroom(CreateClassroomInput input);

    Output updateClassroom(UpdateClassroomInput input);

    Output deleteClassroom(Input input);

    Output addStudentToClassroom(AddStudentToClassroomInput input);

    Output addTeacherToClassroom(AddTeacherToClassroomInput input);

    List<User> getListStudentInClass(String idClassroom);

    Output importScoreFromCSV(ImportScoreFromCSVInput input);

}
