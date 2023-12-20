package com.pmmnm.StudentManagement.adapter.web.v1.controller;

import com.pmmnm.StudentManagement.adapter.web.base.RestApiV1;
import com.pmmnm.StudentManagement.adapter.web.base.RestData;
import com.pmmnm.StudentManagement.adapter.web.base.VsResponseUtil;
import com.pmmnm.StudentManagement.application.constants.UrlConstant;
import com.pmmnm.StudentManagement.application.input.classroom.*;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.service.IClassroomService;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class ClassroomController {

    private final IClassroomService classroomService;

    public ClassroomController(IClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @Operation(summary = "API Get List Classroom")
    @GetMapping(UrlConstant.Classroom.GET_ALL)
    public ResponseEntity<RestData<?>> getClassrooms() {
        List<Classroom> output = classroomService.findAll();
        return VsResponseUtil.ok(output);
    }

    @Operation(summary = "API Get Detail Classroom")
    @GetMapping(UrlConstant.Classroom.GET)
    public ResponseEntity<?> getClassroomById(@PathVariable("idClassroom") String idClassroom) {
        Input input = new Input(idClassroom);
        return VsResponseUtil.ok(classroomService.getDetailClassroom(input));
    }

    @Operation(summary = "API Create Classroom")
    @PostMapping(UrlConstant.Classroom.CREATE)
    public ResponseEntity<?> createClassroom(@RequestBody CreateClassroomInput input) {
        Output output = classroomService.createClassroom(input);
        return VsResponseUtil.ok(output);
    }

    @Operation(summary = "API Update Classroom")
    @PatchMapping(UrlConstant.Classroom.UPDATE)
    public ResponseEntity<?> updateClassroom(@RequestBody UpdateClassroomInput input) {
        Output output = classroomService.updateClassroom(input);
        return VsResponseUtil.ok(output);
    }

    @Operation(summary = "API Delete Classroom")
    @DeleteMapping(UrlConstant.Classroom.DELETE)
    public ResponseEntity<?> deleteClassroom(@PathVariable("idClassroom") String idClassroom) {
        Input input = new Input(idClassroom);
        Output output = classroomService.deleteClassroom(input);
        return VsResponseUtil.ok(output);
    }


    @Operation(summary = "API Get List Student In Classroom")
    @GetMapping(UrlConstant.Classroom.GET_LIST_STUDENT_IN_CLASS)
    public ResponseEntity<?> getListStudentInClass(@PathVariable("idClassroom") String idClassroom) {
        return VsResponseUtil.ok(classroomService.getListStudentInClass(idClassroom));
    }

}
