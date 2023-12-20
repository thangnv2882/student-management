package com.pmmnm.StudentManagement.application.service;

import com.pmmnm.StudentManagement.application.input.commons.Input;

import com.pmmnm.StudentManagement.application.input.notification.CreateNotificationInput;
import com.pmmnm.StudentManagement.application.input.notification.SendNotificationInput;
import com.pmmnm.StudentManagement.application.input.notification.UpdateNotificationInput;
import com.pmmnm.StudentManagement.application.output.common.Output;

import com.pmmnm.StudentManagement.domain.entity.Notification;
import com.pmmnm.StudentManagement.domain.entity.UserNotification;

import java.util.List;

public interface INotificationService {

    List<Notification> findAll();

    Notification findNotificationById(Input input);

    Output createNotification(CreateNotificationInput input);

    Output updateNotification(UpdateNotificationInput input);

    Output deleteNotification(Input input);

    UserNotification sendNotification(SendNotificationInput input);
}
