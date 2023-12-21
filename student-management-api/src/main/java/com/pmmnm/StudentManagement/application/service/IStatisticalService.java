package com.pmmnm.StudentManagement.application.service;

import com.pmmnm.StudentManagement.application.input.statistical.StatisticalInput;
import com.pmmnm.StudentManagement.application.output.statistical.StatisticalClassroomOutput;

public interface IStatisticalService {

    StatisticalClassroomOutput statisticalByIdClassroom(StatisticalInput input);

}
