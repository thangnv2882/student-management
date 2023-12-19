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
public class User {
    private String id;

    private String name;

    private String password;

    private String email;

    private String role;

    private String avatar;

    private List<UserClassroom> userClassrooms = new ArrayList<>();

    public User(String id) {
        this.id = id;
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
