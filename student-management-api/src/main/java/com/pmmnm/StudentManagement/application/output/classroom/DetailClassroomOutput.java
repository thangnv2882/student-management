package com.pmmnm.StudentManagement.application.output.classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailClassroomOutput {

    private String id;

    private String name;

    private String teacherId;

    private int creditHour;

    private List<UserScoreOutput> userScoreOutputList = new ArrayList<>();

}
