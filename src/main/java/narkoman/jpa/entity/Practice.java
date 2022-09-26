package narkoman.jpa.entity;

import org.w3c.dom.ls.LSOutput;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;


public class Practice {


    private static final Scanner IN = new Scanner(System.in);
    public static void main(String[] args) {
        String categoryName = "Компьютеры";

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        TypedQuery<Category> productTypedQuery = manager.createQuery(
                "select p from Category p where p.name = ?1", Category.class
        );

        productTypedQuery.setParameter(1, categoryName);
        List<Category> categories = productTypedQuery.getResultList();
        System.out.println("Введите название категории: ");
        String line = IN.nextLine();
                if (categories.size() != 0){
            System.out.println("В базе данных есть категория");
        } else {
            System.out.println("В базе данных нету категории ");
        }
    }
}
