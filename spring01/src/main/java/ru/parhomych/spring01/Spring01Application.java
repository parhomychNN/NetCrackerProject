package ru.parhomych.spring01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.parhomych.spring01.model.Student;
import ru.parhomych.spring01.service.StudentService;

import javax.sql.DataSource;

@SpringBootApplication
public class Spring01Application implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    StudentService studentService;


    public static void main(String[] args) {
        SpringApplication.run(Spring01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Student student = studentService.findStudentById(2);
        System.out.println(student);


/*
        System.out.println("DATASOURCE = " + dataSource);

        /// Get dbcp2 datasource settings
        // BasicDataSource newds = (BasicDataSource) dataSource;
        // System.out.println("BasicDataSource = " + newds.getInitialSize());

        System.out.println("Display all customers...");
        List<Transaction> list = transactionsRepository.findAll();
        list.forEach(x -> System.out.println(x));

        System.out.println("Done!");

*/

/*        Lesson lesson = new Lesson();
        System.out.println(lesson);*/
        //exit(0);
    }
}
