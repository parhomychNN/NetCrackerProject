package ru.parhomych.spring01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.parhomych.spring01.model.Student;
import ru.parhomych.spring01.service.StudentService;

import javax.sql.DataSource;

@SpringBootApplication
public class Spring01Application{

    public static void main(String[] args) {
        SpringApplication.run(Spring01Application.class, args);
    }

}
