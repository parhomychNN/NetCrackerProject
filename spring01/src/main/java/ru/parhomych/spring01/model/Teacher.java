package ru.parhomych.spring01.model;

import lombok.Data;

@Data
public class Teacher {
    private int teacherId;
    private String firstName;
    private String lastName;
    private String academicDegree;
}
