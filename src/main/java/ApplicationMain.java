import narkoman.jpa.entity.Category;
import narkoman.jpa.entity.Option;
import narkoman.jpa.entity.Product;
import narkoman.jpa.entity.Value;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

import static java.awt.SystemColor.menu;

public class ApplicationMain {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("main");

    private static final Scanner IN = new Scanner(System.in);
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");

    public static void main(String[] args) {

        while (true) {
            System.out.println("Выберите действие: ");
            System.out.println("- Создание [1]");
            System.out.println("- Редактирование [2]");
            System.out.println("- Удаление [3]");
            System.out.println("- Завершение работы [4]");
            String actionNum = IN.nextLine();
            switch (actionNum) {
                case "1" -> create();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> {
                    System.out.println("Программа завершена!");
                    System.exit(0);
                }
                default -> System.out.println("Такого действия не существует");
            }
        }
    }
    private static void create() {
        EntityManager manager = FACTORY.createEntityManager();
        try {
            manager.getTransaction().begin();
            TypedQuery<Category> categoryTypedQuery1 = manager.createQuery("select p from Category p", Category.class);
            List<Category> categories_name = categoryTypedQuery1.getResultList();
            for (int i = 0; i < categories_name.size(); i++) {
                System.out.println(categories_name.get(i).getName() + " " + "[" + categories_name.get(i).getId() + "]");
            }
            System.out.println("Введите категорию по номеру: ");
            String line = IN.nextLine();
            Long convertor = Long.parseLong(line);
            Category category = manager.find(Category.class, convertor);

            Product product = new Product();
            System.out.println("Введите название товара: ");
            String line2 = IN.nextLine();
            product.setProductName(line2);
            product.setCategory(category);

            System.out.println("Введите стоимость товара: ");
            String line3 = IN.nextLine();
            boolean result = line3.matches("\\d+");
            while (result != true) {
                System.out.println("Вы некорректно ввели стоимость товара, повторите попытку: ");
                line3 = IN.nextLine();
                boolean result2 = line3.matches("\\d+");
                if (result2 == true) {
                    System.out.println("Цена введена корректно!");
                    break;
                }
            }
            Integer price_convert = Integer.parseInt(line3);
            product.setPrice(price_convert);
            System.out.println("Товар успешно создан");
            manager.persist(product);

            TypedQuery<Option> optionTypedQuery = manager.createQuery(
                    "select c from Option c where c.category.id = :selectId", Option.class
            );
            optionTypedQuery.setParameter("selectId", convertor);
            List<Option> options = optionTypedQuery.getResultList();
            for (Option option : options) {
                System.out.println("Введите название о характеристике " + " [" + option.getOptionName() + "] ");
                Value values = new Value();
                String value_add = IN.nextLine();
                values.setValue(value_add);
                values.setOption(option);
                values.setProduct(product);
                manager.persist(values);
            }
            manager.persist(product);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    private static void update() {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();

            System.out.println("Введите id товара: ");
            String search_id = IN.nextLine();
            Long update_id = Long.parseLong(search_id);
            Product productTypedQuery = manager.find(Product.class, update_id);

            System.out.println("Введите новое название товара: ");
            String update_name = IN.nextLine();
            productTypedQuery.setProductName(update_name);

            System.out.println("Введите новую цену для товара: ");
            String update_price = IN.nextLine();
            Integer new_price = Integer.parseInt(update_price);
            productTypedQuery.setPrice(new_price);

            System.out.println("Введите новые характеристики: ");
            TypedQuery<Option> optionTypedQuery = manager.createQuery("select a from Option a where a.category.id = ?1", Option.class);
            optionTypedQuery.setParameter(1, productTypedQuery.getCategory().getId());
            List<Option> options = optionTypedQuery.getResultList();


            for (int i = 0; i < options.size(); i++) {
                TypedQuery<Value> valueTypedQuery = manager.createQuery("select a from Value a where a.product.id = :id and a.option.id = :option", Value.class);
                valueTypedQuery.setParameter("id", update_id);
                valueTypedQuery.setParameter("option", options.get(i).getId());
                if (valueTypedQuery.getResultList().size() == 0) {
                    Value value = new Value();
                    System.out.println("Введите недостающие характеристики: " + "[ " + options.get(i).getOptionName() + " ]");
                    String add_new_value = IN.nextLine();
                    value.setValue(add_new_value);
                    value.setOption(options.get(i));
                    value.setProduct(productTypedQuery);
                    manager.persist(value);

                } else {
                    System.out.println("Обновите характеристику " + " [" + options.get(i).getOptionName() + "] ");
                    String update_option = IN.nextLine();
                    valueTypedQuery.getResultList().get(0).setValue(update_option);
                }
            }
            System.out.println("Обновление прошло успешно!");
            manager.getTransaction().commit();
        } catch (Exception f) {
            manager.getTransaction().rollback();
            f.printStackTrace();
        }
    }

    private static void delete() {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            System.out.println("Введите ID товара для удаления: ");
            String get_id = IN.nextLine();
            Long id_convert = Long.parseLong(get_id);
            Product product = manager.find(Product.class, id_convert);
            if (product != null) {
                Query query = manager.createQuery(
                        "delete from Value a where a.product.id = :a"
                );
                query.setParameter("a", product.getId());
                query.executeUpdate();
                Query query1 = manager.createQuery(
                        "delete from Product p where p.id = :b"
                );
                query1.setParameter("b", product.getId());
                query1.executeUpdate();
            }
            manager.getTransaction().commit();
            System.out.println("Товар успешно удалён.");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
