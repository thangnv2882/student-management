package com.pmmnm.StudentManagement.application.service;

import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.input.statistical.StatisticalInput;
import com.pmmnm.StudentManagement.application.input.user.CreateUserInput;
import com.pmmnm.StudentManagement.application.input.user.EnterScoreInput;
import com.pmmnm.StudentManagement.application.input.user.LoginInput;
import com.pmmnm.StudentManagement.application.input.user.UpdateUserInput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.output.statistical.StatisticalClassroomOutput;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import com.pmmnm.StudentManagement.domain.entity.User;

import java.util.List;

public interface IStatisticalService {

    StatisticalClassroomOutput statisticalByIdClassroom(StatisticalInput input);

}
