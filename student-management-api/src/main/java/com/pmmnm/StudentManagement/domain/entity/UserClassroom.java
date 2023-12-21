package com.pmmnm.StudentManagement.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserClassroom {

    private String idClassroom;

    private String idUser;

    private Double score;

    public UserClassroom(String idClassroom, String idUser) {
        this.idClassroom = idClassroom;
        this.idUser = idUser;
    }
}
