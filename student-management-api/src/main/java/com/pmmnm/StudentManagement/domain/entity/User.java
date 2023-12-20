package com.pmmnm.StudentManagement.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public User(String id) {
        this.id = id;
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
