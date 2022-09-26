package narkoman.jpa;

import narkoman.jpa.entity.Category;
import narkoman.jpa.entity.Product;

import javax.naming.Name;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.xml.namespace.QName;
import java.util.List;

public class Application2 {

    public static void main(String[] args) {

        // JPA - стандарт описывающий взаимодействие Java с реляционными базами данных по принципу ORM. JPA решает
        // несколько изъянов JDBC такие как: привязка к определенному диалекту, взаимодействие с данными через
        // неудобный для использования ResultSet (тип определяется в момент обращения) и т.д.

        // ORM (Object Relational Mapping) - система объектно реляционного сопоставления основной целью которой является
        // преобразование табличных данных к Java объектам и наоборот.


        // table users             -> ORM  -> class User
        // id serial8              -> ORM  -> Long id
        // first_name varchar      -> ORM  -> String firstName
        // birthdate date          -> ORM  -> LocalDate birthdate

        // Т.к. JPA это просто стандарт написанный на бумаге (т.е. мы не можем просто взять JPA и использовать т.к. это
        // не конкретный рабочий прототип) необходимо использовать сторонние реализации этого стандарта.

        // Реализации JPA:
        // 1. Hibernate;
        // 2. EclipseLink;
        // 3. ...;


        // persistence.xml - основной конфигурационный файл стандарта JPA который хранит в себе блоки с параметрами
        // подключения. Данный файл конфигурации должен находится в папке с названием META-INF которая в свою очередь
        // должна попасть в итоговую сборку.

        // 'EntityManagerFactory' - объект основной задачей которого является открытие нового соединения с базой данных
        // и поддержание его в открытом виде с целью получения объектов EntityManager.

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");

        // EntityManager - объект позволяющий производить манипуляции непосредственно с сущностями (создавать, изменять,
        // удалять и выбирать).

        // Сущность - Java класс объекты которого взаимодействуют с базой данных т.е. являются отражением таблиц в JPA.


        EntityManager manager = factory.createEntityManager();

        // 'manager.find(Class<T> aClass, Object o) : T' - производит поиск сущности типа из параметра 'aClass' со значением
        // первичного ключа из параметра 'o'.

        // Category.class -> Class<Category>
        // Product.class -> Class<Product>

        // В базе данных для взаимодействия с ней используется язык специального назначения SQL. Язык SQL может иметь различные
        // особенности для разных баз данных.

        // JPQL - язык из стандарта JPA предназначенный для написания запросов и взаимодействия сущностями. JPQL это универсальный
        // язык для работы с базой данных в рамках стандарта JPA.

        // JPQL -> ORM ->  MySQL Dialect -> MySQL
        // JPQL -> ORM -> Oracle DB Dialect -> Oracle DB
        // JPQL -> ORM -> Microsoft SQL Server Dialect -> Microsoft SQL Server

        // SQL -> select * from products
        // JPQL -> select p from Product p

        // SQL -> select * from products where price >= 100000 and price <= 200000
        // JPQL -> select p from Products p where p.price >= 100000 and p.price <= 200000

        // 'TypedQuery<X>' - объект предназначенный для выполнения запросов с четко определенным результатом, тип результата
        // указывается в аргументе 'X'. Применяется для выполнения запросов на выборку данных т.е. select.

        // 'entityManager.createQuery(String s, Class<T> w) : TypedQuery<T>' - производит создание нового объекта 'TypedQuery' на
        // основании JPQL запроса из параметра 's' и с типом результата из параметра 'w'.

//        TypedQuery<Category> categoryTypedQuery = manager.createQuery(
//          "select c from Category c", Category.class
//        );
//        List<Category> categories = categoryTypedQuery.getResultList();
//        for (Category category : categories){
//            System.out.println(category.getName());
//        }

// Параметры запроса - условные обозначения в запросе на место которых в дальнейшем должна быть подставлена конкретная информация.
// Параметры в JPQL бывают двух видов:
        // 1. Порядковые - каждый параметр должен быть пронумерован числом начиная от единицы. Такие параметры создаются при
        // помощи вопросительного знака.
        // 2. Именованные - каждый параметр должен иметь свое название при условии что это соответствует правилам составления
        // идентификатора. Такие параметры создаются при помощи двоеточия.


//        TypedQuery<Product> productTypedQuery = manager.createQuery(
//                "select a from Product a where a.price between ?1 and ?2", Product.class
//                );
//        productTypedQuery.setParameter(1, 500);
//        productTypedQuery.setParameter(2, 50000);
//        List<Product> products = productTypedQuery.getResultList();
//        for(Product product : products){
//            System.out.println(product.getProductName());
//        }

//        TypedQuery<Product> productTypedQuery = manager.createQuery(
//    "select p from Product p where p.price between :minPrice and :maxPrice", Product.class
//        );
//        productTypedQuery.setParameter("minPrice", 500);
//        productTypedQuery.setParameter("maxPrice", 50000);
//        List<Product> products = productTypedQuery.getResultList();
//        for(Product product : products){
//            System.out.println(product.getProductName());
//        }

//        TypedQuery<Product> productTypedQuery= manager.createQuery(
//          "select p from Product p where p.price between 500 and 50000", Product.class
//        );
//        List<Product> products = productTypedQuery.getResultList();
//        for(Product product : products){
//           System.out.println(product.getProductName());
//       }
        // select * from categories where id = 1
        // L - преобразовывает тип данных к Long.
//        Category category = manager.find(Category.class, 1L);
//        System.out.println("[Категория " + category.getId() + "]" + " -> " + category.getName());

//        List<Product> products = category.getProducts();
//        int average = 0;
//        for (int i = 0; i < products.size(); i++) {
//            average += products.get(i).getPrice();
//            System.out.println(products.get(i).getProductName());
//        }
//        System.out.println(average / products.size());



//        List<Product> maxPrice = category.getProducts();
//        int max = 0;
//        for (int i = 0; i < maxPrice.size(); i++) {
//            if(maxPrice.get(i).getPrice() > max){
//                max = maxPrice.get(i).getPrice();
//            }
//        }
//        System.out.println("[Максимальная цена] -> " + max);

//        List<Product> minPrice = category.getProducts();
//        int min = 0;
//        for (int a = 0; a != minPrice.size(); a++) {
//            if (min > minPrice.get(a).getPrice()){
//                min = minPrice.get(a).getPrice();
//            }
//        }
//        System.out.println("[Минимальная цена] -> " + min);

    }
}
