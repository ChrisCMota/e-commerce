-- create database ecommerce;

create table categories(
    id_category int not null auto_increment primary key,
    name_category varchar(50) not null
);

create table products(
    id_product int not null auto_increment primary key,
    name_product varchar(50) not null,
    description_product text,
    price_product double,
    featured int,
    available int
);

create table category_products(
    id_category int,
    id_product int,

    constraint pk_category_product primary key (id_category, id_product),
    constraint fk_category foreign key (id_category) references categories(id_category),
    constraint fk_product foreign key (id_product) references products(id_product)
);

create table variant_products(
    id_variant int not null auto_increment primary key,
    name_variant varchar(50) not null,
    description_variant text,
    link_photo varchar(255),
    id_product int,

    constraint fk_variant_pdt foreign key (id_product) references products(id_product)
);

create table customers(
    id_customer int not null auto_increment primary key,
    name_customer varchar(50) not null,
    email_customer varchar(100) not null unique,
    phone_number varchar(20),
    address_customer varchar(255) not null,
    aircode_customer varchar(10),
    country varchar(50),
    city varchar(50)
);

create table customer_order(
    id_order int not null auto_increment primary key,
    date_order date not null,
    gross_value double not null,
    discount double not null,
    net_value double not null,
    status int not null,
    id_customer int,

   constraint fk_customer_order foreign key (id_customer) references customers(id_customer)
);

create table item_order(
    num_seq int not null auto_increment primary key,
    unit_price double not null,
    quantity int not null,
    total_price double not null,
    id_variant int,
    id_order int,

    constraint fk_item_order foreign key (id_order) references customer_order(id_order),
    constraint fk_item_variant foreign key (id_variant) references variant_products(id_variant)
);

create table user_account(
    id_user int not null auto_increment primary key,
    username varchar(50) not null unique,
    password varchar(100) not null
);

