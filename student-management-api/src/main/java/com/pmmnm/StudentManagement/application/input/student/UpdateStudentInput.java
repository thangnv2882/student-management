package com.pmmnm.StudentManagement.application.input.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentInput {

    private String id;

    private String name;

    private String phoneNumber;

}
