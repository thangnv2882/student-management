package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.domain.entity.Notification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.pmmnm.StudentManagement.application.utils.DB4OUtil.getObjectContainer;

@Repository
public class NotificationRepository {

    private final ObjectContainer db = getObjectContainer();

    public void save(Object notification) {
        db.store(notification);
    }

    public void delete(Notification notification) {
        db.delete(notification);
    }

    public List<Notification> findAll() {
        List<Notification> notifications = new ArrayList<>();
        ObjectSet<Notification> result = db.query(Notification.class);

        while (result.hasNext()) {
            notifications.add(result.next());
        }
        return notifications;
    }

    public Notification findById(String id) {
        Notification notification = new Notification(id);
        ObjectSet<Notification> result = db.queryByExample(notification);
        return result.hasNext() ? result.next() : null;
    }
}
