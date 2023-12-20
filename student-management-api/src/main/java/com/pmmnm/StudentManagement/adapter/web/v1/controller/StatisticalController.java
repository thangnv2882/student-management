package com.pmmnm.StudentManagement.adapter.web.v1.controller;

import com.pmmnm.StudentManagement.adapter.web.base.RestApiV1;
import com.pmmnm.StudentManagement.adapter.web.base.VsResponseUtil;
import com.pmmnm.StudentManagement.application.constants.UrlConstant;
import com.pmmnm.StudentManagement.application.input.statistical.StatisticalInput;
import com.pmmnm.StudentManagement.application.output.statistical.StatisticalClassroomOutput;
import com.pmmnm.StudentManagement.application.service.IStatisticalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class StatisticalController {

    private final IStatisticalService statisticalService;

    public StatisticalController(IStatisticalService statisticalService) {
        this.statisticalService = statisticalService;
    }

    @Operation(summary = "API Statistical Student In Classroom")
    @GetMapping(UrlConstant.Statistical.STATISTICAL_ACADEMIC_ABILITY)
    public ResponseEntity<?> statisticalByClassroom(@PathVariable("idClassroom") StatisticalInput input) {
        StatisticalClassroomOutput output = statisticalService.statisticalByIdClassroom(input);
        return VsResponseUtil.ok(output);
    }
}
