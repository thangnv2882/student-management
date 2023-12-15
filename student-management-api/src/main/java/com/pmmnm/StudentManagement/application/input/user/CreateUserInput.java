package com.pmmnm.StudentManagement.application.input.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserInput {

    private String id;

    private String name;

    private String role;

}
