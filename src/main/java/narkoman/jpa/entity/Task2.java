package narkoman.jpa.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task2 {

    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Catalog", "postgres", "1488"
        );

    }
}

//
