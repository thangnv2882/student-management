package com.pmmnm.StudentManagement.application.input.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnterScoreInput {

    private String idClassroom;

    private String idStudent;

    private Integer score;

}
