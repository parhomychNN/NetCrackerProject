package ru.parhomych.spring01.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Transaction {

    int id;
    String sourceOfIncomeName;
    Date dateOfIncome;
    int payment;
    String activityName;

}
