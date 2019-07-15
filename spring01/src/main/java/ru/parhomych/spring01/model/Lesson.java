package ru.parhomych.spring01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private int id;
    private Teacher teacher;
    private Student student;
    private String subject;
    private Date date;
    private double price;
}
