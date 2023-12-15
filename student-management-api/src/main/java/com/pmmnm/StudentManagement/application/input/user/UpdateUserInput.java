package com.pmmnm.StudentManagement.application.input.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInput {

    private String id;

    private String name;

    private String password;

    private String email;

    private String avatar;

}
