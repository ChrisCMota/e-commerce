INSERT INTO categories (id_category, name_category)
VALUES (1, 'Tech');

INSERT INTO categories (id_category, name_category)
VALUES (2, 'Game');

INSERT INTO categories (id_category, name_category)
VALUES (3, 'Others');

INSERT INTO products (id_product, name_product, price_product)
VALUES (1, 'MacBook Pro', 3500);

INSERT INTO products (id_product, name_product, price_product)
VALUES (2, 'World Of Warcraft', 150);

INSERT INTO products (id_product, name_product, price_product)
VALUES (3, 'HDMI Cable', 10);

INSERT INTO products (id_product, name_product, price_product)
VALUES (4, 'Laptop Razer', 4500);

INSERT INTO products (id_product, name_product, price_product)
VALUES (5, 'The Last of Us', 120);

INSERT INTO category_products (id_category, id_product)
VALUES (1, 1);

INSERT INTO category_products (id_category, id_product)
VALUES (2, 2);

INSERT INTO category_products (id_category, id_product)
VALUES (3, 3);

INSERT INTO category_products (id_category, id_product)
VALUES (1, 4);

INSERT INTO category_products (id_category, id_product)
VALUES (2, 5);