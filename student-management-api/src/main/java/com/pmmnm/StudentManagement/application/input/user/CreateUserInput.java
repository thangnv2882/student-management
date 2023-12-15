package com.pmmnm.StudentManagement.application.input.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentInput {

    String id;

    String name;

    String phoneNumber;

}
