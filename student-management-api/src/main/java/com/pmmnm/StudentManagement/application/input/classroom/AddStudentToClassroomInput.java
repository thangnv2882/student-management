package com.pmmnm.StudentManagement.application.input.classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentToClassroomInput {

    private String idClassroom;

    private String idUser;

}
