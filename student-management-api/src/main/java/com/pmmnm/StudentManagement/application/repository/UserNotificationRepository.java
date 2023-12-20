package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.domain.entity.Notification;
import com.pmmnm.StudentManagement.domain.entity.User;
import com.pmmnm.StudentManagement.domain.entity.UserNotification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.pmmnm.StudentManagement.application.utils.DB4OUtil.getObjectContainer;

@Repository
public class UserNotificationRepository {

    private final ObjectContainer db = getObjectContainer();
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public UserNotificationRepository(UserRepository userRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }


    public void save(UserNotification userNotification) {
        db.store(userNotification);
    }

    public void delete(UserNotification userNotification) {
        db.delete(userNotification);
    }
}
