package narkoman.jpa.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class Filter {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();


//        System.out.println("Введите категорию: ");
//        long line = Integer.parseInt(IN.nextLine());
//
//        System.out.println("Введите минимальную цену: ");
//        int min = IN.nextInt();
//
//        System.out.println("Введите максимальную цену: ");
//        int max = IN.nextInt();
//
//        TypedQuery<Product> productTypedQuery = manager.createQuery(
//                "select a from Product a where a.category.id = ?1 and a.price between ?2 and ?3", Product.class
//        );
//        productTypedQuery.setParameter(1, line);
//        productTypedQuery.setParameter(2, min);
//        productTypedQuery.setParameter(3, max);
//        List<Product> products = productTypedQuery.getResultList();
//        for (int i = 0; i < products.size(); i++) {
//            System.out.println(products.get(i).getProductName());
//        }

        // Вывести товары в названии которых находится Samsung
//        String name = "Samsung";
//        TypedQuery<Product> productTypedQuery = manager.createQuery(
//                "select p from Product p where p.productName like :name", Product.class
//        );
//        productTypedQuery.setParameter("name" , "%" + name + "%");
//        List<Product> products = productTypedQuery.getResultList();
//        for(Product product : products){
//            System.out.println(product.getProductName());
//        }

        // try...catch - конструкция применяемая для отлавливания ошибок в определенном участке кода.
        // try {...} -> кодовый блок проверяемый на возникновение ошибок.
        // catch (...) {...} -> кодовый блок в котором указывается тип ошибки и реакция на ее возникновение.

        // Exception - базовый класс для любых ошибок в Java программе возникающая во время выполнения программы.

        try {
            manager.getTransaction().begin();

//            Category category = new Category();
//            category.setName("Смартфоны");
//            manager.persist(category);

            // Тип результата createQuery зависит от того что указано в конструкции select

            // select c from Category -> Category.class

            // select c.name from Category c -> String.class

            // createQuery("select c.name from Category c", Category.class)

            Category category = manager.find(Category.class, 3L);
            category.setName("Ультрабуки");

            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace(); // -> Выводит все ошибки найденные во время работы программы.
        }
    }
}