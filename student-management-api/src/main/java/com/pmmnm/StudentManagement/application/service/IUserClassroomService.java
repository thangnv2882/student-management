package com.pmmnm.StudentManagement.application.service;

import com.pmmnm.StudentManagement.application.input.userClassroom.*;
import com.pmmnm.StudentManagement.application.output.common.Output;

public interface IUserClassroomService {

    Output addStudentToClassroom(AddStudentToClassroomInput input);
    Output removeStudentFromClassroom(RemoveStudentFromClassroomInput input);

    Output addTeacherToClassroom(AddTeacherToClassroomInput input);

    Output enterScore(EnterScoreInput input);

    Output importScoreFromCSV(ImportScoreFromCSVInput input);

}
