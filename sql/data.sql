insert into products(product_name, product_price, category_id)
values ('Intel Core I9 9900', 1000, 1),
       ('AMD Ryzen 7 3700X', 500, 1),
       ('Samsung MZ23U255', 250, 2),
       ('AOC L215U266', 150, 2),
       ('TN-Film', 80, 2),
       ('AH-IPS', 100, 2);

insert into categories(category_name)
values ('Процессоры'), -- 1
       ('Мониторы'); -- 2

insert into options(options_name, category_id)
values ('Производитель', 1),
       ('Сокет', 1),
       ('Максимальный объём ОЗУ', 1),
       ('Тактовая частота', 1),
       ('Производитель', 2),
       ('Диагональ', 2),
       ('Матрица', 2),
       ('Материал подставки', 2);

insert into value(value, option_id, product_id)
values ('Intel', 1, 1),
       ('AMD', 2, 2),
       ('1151', 3, 3),
       ('AM4', 4, 4);













