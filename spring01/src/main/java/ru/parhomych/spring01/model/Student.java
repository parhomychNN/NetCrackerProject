package ru.parhomych.spring01.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private Date date;
}
