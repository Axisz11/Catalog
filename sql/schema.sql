create table products
(
    id            serial8,
    product_name  varchar not null,
    product_price int4    not null,
    category_id   int8    not null,
    primary key (id),
    foreign key (category_id) references categories (id)
);
create table categories
(
    id            serial8,
    category_name varchar not null,
    primary key (id)
);

create table options
(
    id           serial8,
    options_name varchar not null,
    category_id  int8    not null,
    primary key (id),
    foreign key (category_id) references categories (id)
);

create table value
(
    id         serial8,
    value      varchar not null,
    option_id  int8    not null,
    product_id int8    not null,
    primary key (id),
    foreign key (option_id) references options (id),
    foreign key (product_id) references products (id)
);