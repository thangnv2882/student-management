package com.pmmnm.StudentManagement.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private String id;

    private String title;

    private String content;

    public Notification(String id) {
        this.id = id;
    }
}
