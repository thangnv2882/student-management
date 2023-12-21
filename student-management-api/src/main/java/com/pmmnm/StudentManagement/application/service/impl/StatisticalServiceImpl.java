package com.pmmnm.StudentManagement.application.service.impl;

import com.pmmnm.StudentManagement.application.input.statistical.StatisticalInput;
import com.pmmnm.StudentManagement.application.output.statistical.StatisticalClassroomOutput;
import com.pmmnm.StudentManagement.application.repository.StatisticalRepository;
import com.pmmnm.StudentManagement.application.repository.UserClassroomRepository;
import com.pmmnm.StudentManagement.application.service.IStatisticalService;
import com.pmmnm.StudentManagement.domain.entity.UserClassroom;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatisticalServiceImpl implements IStatisticalService {

    private final StatisticalRepository statisticalRepository;
    private final UserClassroomRepository userClassroomRepository;
    private final ModelMapper modelMapper;

    public StatisticalServiceImpl(StatisticalRepository statisticalRepository, UserClassroomRepository userClassroomRepository, ModelMapper modelMapper) {
        this.statisticalRepository = statisticalRepository;
        this.userClassroomRepository = userClassroomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatisticalClassroomOutput statisticalByIdClassroom(StatisticalInput input) {
        List<UserClassroom> users = userClassroomRepository.findByIdClassroom(input.getIdClassroom());
        AtomicInteger amountTypeA = new AtomicInteger();
        AtomicInteger amountTypeB = new AtomicInteger();
        AtomicInteger amountTypeC = new AtomicInteger();
        users.forEach((item) -> {
            if(item.getScore() != null && item.getScore() >= 8.5) {
                amountTypeA.getAndIncrement();
            } else if(item.getScore() != null && item.getScore() >= 7) {
                amountTypeB.getAndIncrement();
            } else {
                amountTypeC.getAndIncrement();
            }
        });
        StatisticalClassroomOutput output = new StatisticalClassroomOutput();
        output.setAmountStudentsTypeA(amountTypeA.get());
        output.setAmountStudentsTypeB(amountTypeB.get());
        output.setAmountStudentsTypeC(amountTypeC.get());
        output.setPercentTypeA(1.0 * amountTypeA.get() / users.size() * 100);
        output.setPercentTypeB(1.0 * amountTypeB.get() / users.size() * 100);
        output.setPercentTypeC(1.0 * amountTypeC.get() / users.size() * 100);
        return output;
    }
}
