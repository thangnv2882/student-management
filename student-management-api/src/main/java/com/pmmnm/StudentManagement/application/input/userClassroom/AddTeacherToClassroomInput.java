package com.pmmnm.StudentManagement.application.input.userClassroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTeacherToClassroomInput {

    private String idClassroom;

    private String idTeacher;

}
