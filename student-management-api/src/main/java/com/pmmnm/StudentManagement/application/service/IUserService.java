package com.pmmnm.StudentManagement.application.service;

import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.input.user.CreateUserInput;
import com.pmmnm.StudentManagement.application.input.user.LoginInput;
import com.pmmnm.StudentManagement.application.input.user.UpdateUserInput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import com.pmmnm.StudentManagement.domain.entity.User;

import java.util.List;

public interface IUserService {

    User login(LoginInput loginInput);

    List<User> findAll();

    User findUserById(Input input);

    Output createUser(CreateUserInput input);

    Output updateUser(UpdateUserInput input);

    Output deleteUser(Input input);

    List<Classroom> getListClassOfStudent(String idUser);

}
