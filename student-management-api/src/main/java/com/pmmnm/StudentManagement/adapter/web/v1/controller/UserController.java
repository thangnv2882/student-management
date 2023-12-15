package com.pmmnm.StudentManagement.adapter.web.v1.controller;

import com.pmmnm.StudentManagement.adapter.web.base.RestApiV1;
import com.pmmnm.StudentManagement.adapter.web.base.RestData;
import com.pmmnm.StudentManagement.adapter.web.base.VsResponseUtil;
import com.pmmnm.StudentManagement.application.constants.UrlConstant;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.input.user.CreateUserInput;
import com.pmmnm.StudentManagement.application.input.user.LoginInput;
import com.pmmnm.StudentManagement.application.input.user.UpdateUserInput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.service.IUserService;
import com.pmmnm.StudentManagement.domain.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping(UrlConstant.Auth.LOGIN)
    public ResponseEntity<?> createAuth(@RequestBody LoginInput input) {
        User user = userService.login(input);
        return VsResponseUtil.ok(user);
    }

    @GetMapping(UrlConstant.User.GET_ALL)
    public ResponseEntity<RestData<?>> getUsers() {
        List<User> output = userService.findAll();
        return VsResponseUtil.ok(output);
    }


    @GetMapping(UrlConstant.User.GET)
    public ResponseEntity<?> getUserById(@PathVariable("idUser") String idUser) {
        Input input = new Input(idUser);
        return VsResponseUtil.ok(userService.findUserById(input));
    }

    @PostMapping(UrlConstant.User.CREATE)
    public ResponseEntity<?> createUser(@RequestBody CreateUserInput input) {
        Output output = userService.createUser(input);
        return VsResponseUtil.ok(output);
    }

    @PatchMapping(UrlConstant.User.UPDATE)
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserInput input) {
        Output output = userService.updateUser(input);
        return VsResponseUtil.ok(output);
    }

    @DeleteMapping(UrlConstant.User.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("idUser") String idUser) {
        // Create input
        Input input = new Input(idUser);
        // Get output
        Output output = userService.deleteUser(input);
        // Return output
        return VsResponseUtil.ok(output);
    }

}
