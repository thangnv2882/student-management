package com.pmmnm.StudentManagement.application.service.impl;

import com.pmmnm.StudentManagement.application.constants.CommonConstant;
import com.pmmnm.StudentManagement.application.constants.MessageConstant;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.input.notification.CreateNotificationInput;
import com.pmmnm.StudentManagement.application.input.notification.SendNotificationInput;
import com.pmmnm.StudentManagement.application.input.notification.UpdateNotificationInput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.repository.NotificationRepository;
import com.pmmnm.StudentManagement.application.repository.UserNotificationRepository;
import com.pmmnm.StudentManagement.application.repository.UserRepository;
import com.pmmnm.StudentManagement.application.service.INotificationService;

import com.pmmnm.StudentManagement.config.exception.NotFoundException;

import com.pmmnm.StudentManagement.domain.entity.Notification;

import com.pmmnm.StudentManagement.domain.entity.User;
import com.pmmnm.StudentManagement.domain.entity.UserNotification;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements INotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;

    private final ModelMapper modelMapper;

    public NotificationServiceImpl(UserRepository userRepository, NotificationRepository notificationRepository, UserNotificationRepository userNotificationRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.userNotificationRepository = userNotificationRepository;
        this.modelMapper = modelMapper;
    }
    

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification findNotificationById(Input input) {
        Notification notification = notificationRepository.findById(input.getId());
        checkNotificationExists(notification);
        return notification;
    }

    @Override
    public Output createNotification(CreateNotificationInput input) {
        Notification notification = notificationRepository.findById(input.getId());
        if (notification != null) {
            return new Output(CommonConstant.TRUE, MessageConstant.NOTIFICATION_NOT_EXISTS);
        }
        notification = modelMapper.map(input, Notification.class);

        notificationRepository.save(notification);
        return new Output(CommonConstant.TRUE, CommonConstant.CREATED);
    }

    @Override
    public Output updateNotification(UpdateNotificationInput input) {
        Notification notification = notificationRepository.findById(input.getId());
        checkNotificationExists(notification);
        modelMapper.map(input, notification);
        notificationRepository.save(notification);
        return new Output(CommonConstant.TRUE, CommonConstant.UPDATED);
    }

    @Override
    public Output deleteNotification(Input input) {
        Notification notification = notificationRepository.findById(input.getId());
        checkNotificationExists(notification);
        notificationRepository.delete(notification);
        return new Output(CommonConstant.TRUE, CommonConstant.DELETED);
    }

    @Override
    public UserNotification sendNotification(SendNotificationInput input) {
        Notification notification = notificationRepository.findById(input.getIdNotification());
        checkNotificationExists(notification);
        User user = userRepository.findById(input.getIdUser());
        checkUserExists(user);
        UserNotification userNotification = new UserNotification(input.getIdNotification(), input.getIdUser());
        userNotificationRepository.save(userNotification);
        return userNotification;
    }


    public static void checkNotificationExists(Notification notification) {
        if (notification == null) {
            throw new NotFoundException(MessageConstant.NOTIFICATION_NOT_EXISTS);
        }
    }
    public static void checkUserExists(User user) {
        if (user == null) {
            throw new NotFoundException(MessageConstant.USER_NOT_EXISTS);
        }
    }


}
