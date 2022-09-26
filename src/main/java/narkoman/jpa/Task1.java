package narkoman.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task1 {

    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/pseudo_shop", "postgres", "1488"
        );
//
//        String name = "Андрей";
//
//        String surname = "Одинцов";
//        Statement statement = connection.createStatement();
//        String query = "select * from users";
//        if (statement.execute(query)) {
//            ResultSet resultSet = statement.getResultSet();
//
//            boolean check = false;
//            while (resultSet.next()) {
//                // System.out.println(resultSet.getString("name") + " -> " + name);
//                if (resultSet.getString("name").equals(name) && resultSet.getString("surname").equals(surname)) {
//                    check = true;
//                }
//            }
//            if (check == true) {
//                System.out.println("Запись найдена");
//            } else {
//                System.out.println("Запись не найдена");
//            }
//        }
    }
}

// Отдельно создать narkoman.jpa.entity.Task2 где нужно реализовать эту же самую задачу с применением конструкции where и без цикла