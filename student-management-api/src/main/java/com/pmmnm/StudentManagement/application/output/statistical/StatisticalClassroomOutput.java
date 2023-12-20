package com.pmmnm.StudentManagement.application.output.statistical;

import com.pmmnm.StudentManagement.domain.entity.User;
import com.pmmnm.StudentManagement.domain.entity.UserClassroom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticalClassroomOutput {

    private Double percentTypeA;

    private Integer amountStudentsTypeA;

    private Double percentTypeB;

    private Integer amountStudentsTypeB;

    private Double percentTypeC;

    private Integer amountStudentsTypeC;

}
