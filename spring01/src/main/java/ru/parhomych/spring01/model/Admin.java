package ru.parhomych.spring01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private int id;
    private String firstName;
    private String lastName;
    private String position;

}
