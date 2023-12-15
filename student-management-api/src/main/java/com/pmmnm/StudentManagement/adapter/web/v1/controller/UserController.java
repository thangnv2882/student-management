package com.pmmnm.StudentManagement.adapter.web.v1.controller;

import com.pmmnm.StudentManagement.adapter.web.base.RestApiV1;
import com.pmmnm.StudentManagement.adapter.web.base.RestData;
import com.pmmnm.StudentManagement.adapter.web.base.VsResponseUtil;
import com.pmmnm.StudentManagement.application.constants.UrlConstant;
import com.pmmnm.StudentManagement.application.input.user.CreateUserInput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.service.IUserService;
import com.pmmnm.StudentManagement.domain.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestApiV1
public class StudentController {

    private final IUserService studentService;

    public StudentController(IUserService StudentService) {
        this.studentService = StudentService;
    }

    @GetMapping(UrlConstant.Student.GET_ALL)
    public ResponseEntity<RestData<?>> getStudents() {
        // Get output
        List<User> output = studentService.findAll();
        // Return output
        return VsResponseUtil.ok(output);
    }

    @PostMapping(UrlConstant.Student.CREATE)
    public ResponseEntity<?> createStudent(@RequestBody CreateUserInput input) {
        // Get output
        Output output = studentService.createStudent(input);
        // Return output
        return VsResponseUtil.ok(output);
    }

}
