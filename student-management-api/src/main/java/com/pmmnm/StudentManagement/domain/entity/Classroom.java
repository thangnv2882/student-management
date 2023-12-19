package com.pmmnm.StudentManagement.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
    private String id;

    private String name;

    private String teacherId;

    private int creditHour;

    private List<UserClassroom> userClassrooms = new ArrayList<>();

    public Classroom(String id) {
        this.id = id;
    }
}
