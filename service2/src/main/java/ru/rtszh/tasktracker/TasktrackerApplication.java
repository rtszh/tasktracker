package ru.rtszh.tasktracker;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TasktrackerApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(TasktrackerApplication.class, args);

        Console.main(args);
    }

}
