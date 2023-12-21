package com.pmmnm.StudentManagement.application.input.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendNotificationInput {

    private String idNotification;

    private String idUser;

}
