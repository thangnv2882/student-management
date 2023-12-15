package com.pmmnm.StudentManagement.application.input.classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClassroomInput {

    private String id;

    private String name;

    private Integer creditHour;

}
