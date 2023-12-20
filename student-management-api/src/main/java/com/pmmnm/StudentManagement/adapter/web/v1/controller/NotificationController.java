package com.pmmnm.StudentManagement.adapter.web.v1.controller;

import com.pmmnm.StudentManagement.adapter.web.base.RestApiV1;
import com.pmmnm.StudentManagement.adapter.web.base.RestData;
import com.pmmnm.StudentManagement.adapter.web.base.VsResponseUtil;
import com.pmmnm.StudentManagement.application.constants.UrlConstant;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.input.notification.CreateNotificationInput;
import com.pmmnm.StudentManagement.application.input.notification.SendNotificationInput;
import com.pmmnm.StudentManagement.application.input.notification.UpdateNotificationInput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.service.INotificationService;
import com.pmmnm.StudentManagement.domain.entity.Notification;
import com.pmmnm.StudentManagement.domain.entity.UserNotification;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class NotificationController {

    private final INotificationService notificationService;

    public NotificationController(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "API Get List Notification")
    @GetMapping(UrlConstant.Notification.GET_ALL)
    public ResponseEntity<RestData<?>> getNotifications() {
        List<Notification> output = notificationService.findAll();
        return VsResponseUtil.ok(output);
    }


    @Operation(summary = "API Get Notification By Id")
    @GetMapping(UrlConstant.Notification.GET)
    public ResponseEntity<?> getNotificationById(@PathVariable("idNotification") String idNotification) {
        Input input = new Input(idNotification);
        return VsResponseUtil.ok(notificationService.findNotificationById(input));
    }

    @Operation(summary = "API Send Notification To User")
    @PostMapping(UrlConstant.Notification.SEND)
    public ResponseEntity<?> sendNotification(@RequestBody SendNotificationInput input) {
        UserNotification output = notificationService.sendNotification(input);
        return VsResponseUtil.ok(output);
    }

    @Operation(summary = "API Create Notification")
    @PostMapping(UrlConstant.Notification.CREATE)
    public ResponseEntity<?> createNotification(@RequestBody CreateNotificationInput input) {
        Output output = notificationService.createNotification(input);
        return VsResponseUtil.ok(output);
    }

    @Operation(summary = "API Update Notification")
    @PatchMapping(UrlConstant.Notification.UPDATE)
    public ResponseEntity<?> updateNotification(@RequestBody UpdateNotificationInput input) {
        Output output = notificationService.updateNotification(input);
        return VsResponseUtil.ok(output);
    }

    @Operation(summary = "API Delete Notification")
    @DeleteMapping(UrlConstant.Notification.DELETE)
    public ResponseEntity<?> deleteNotification(@PathVariable("idNotification") String idNotification) {
        // Create input
        Input input = new Input(idNotification);
        // Get output
        Output output = notificationService.deleteNotification(input);
        // Return output
        return VsResponseUtil.ok(output);
    }
}
