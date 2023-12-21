package com.pmmnm.StudentManagement.adapter.web.v1.controller;

import com.pmmnm.StudentManagement.adapter.web.base.RestApiV1;
import com.pmmnm.StudentManagement.adapter.web.base.VsResponseUtil;
import com.pmmnm.StudentManagement.application.constants.UrlConstant;
import com.pmmnm.StudentManagement.application.input.userClassroom.*;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.service.IUserClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestApiV1
public class UserClassroomController {

    private final IUserClassroomService userClassroomService;

    public UserClassroomController(IUserClassroomService userClassroomService) {
        this.userClassroomService = userClassroomService;
    }

    @Operation(summary = "API Add Student To Classroom")
    @PostMapping(UrlConstant.UserClassroom.ADD_STUDENT_TO_CLASSROOM)
    public ResponseEntity<?> addStudentToClassroom(@RequestBody AddStudentToClassroomInput input) {
        Output output = userClassroomService.addStudentToClassroom(input);
        return VsResponseUtil.ok(output);
    }

    @Operation(summary = "API Remove Student From Classroom")
    @PostMapping(UrlConstant.UserClassroom.REMOVE_STUDENT_FROM_CLASSROOM)
    public ResponseEntity<?> addStudentToClassroom(@RequestBody RemoveStudentFromClassroomInput input) {
        Output output = userClassroomService.removeStudentFromClassroom(input);
        return VsResponseUtil.ok(output);
    }

    @Operation(summary = "API Add Teacher To Classroom")
    @PostMapping(UrlConstant.UserClassroom.ADD_TEACHER_TO_CLASSROOM)
    public ResponseEntity<?> removeStudentFromClassroom(@RequestBody AddTeacherToClassroomInput input) {
        Output output = userClassroomService.addTeacherToClassroom(input);
        return VsResponseUtil.ok(output);
    }

    @Operation(summary = "API Enter Score")
    @PostMapping(UrlConstant.UserClassroom.ENTER_SCORE)
    public ResponseEntity<?> enterScore(@RequestBody EnterScoreInput input) {
        return VsResponseUtil.ok(userClassroomService.enterScore(input));
    }

    @Operation(summary = "API Import Score From CSV")
    @PostMapping(UrlConstant.UserClassroom.IMPORT_SCORE_FROM_CSV)
    public ResponseEntity<?> importScoreFromCSV(@ModelAttribute ImportScoreFromCSVInput input) {
        return VsResponseUtil.ok(userClassroomService.importScoreFromCSV(input));
    }

}
