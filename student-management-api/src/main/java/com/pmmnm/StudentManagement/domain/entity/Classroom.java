package com.pmmnm.StudentManagement.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
    private String id;

    private String name;

    private String teacherId;

    private int creditHour;

    public Classroom(String id) {
        this.id = id;
    }
}
