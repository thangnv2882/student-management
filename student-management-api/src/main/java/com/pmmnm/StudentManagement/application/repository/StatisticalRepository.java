package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.application.output.statistical.StatisticalClassroomOutput;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import com.pmmnm.StudentManagement.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.pmmnm.StudentManagement.application.utils.DB4OUtil.getObjectContainer;

@Repository
public class StatisticalRepository {

    private final ObjectContainer db = getObjectContainer();

}
