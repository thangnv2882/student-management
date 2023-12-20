package com.pmmnm.StudentManagement.application.input.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationInput {

    private String id;

    private String title;

    private String content;

}
