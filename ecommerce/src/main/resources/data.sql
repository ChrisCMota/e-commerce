INSERT INTO categories (name_category)
VALUES ( 'Tech');

INSERT INTO categories (name_category)
VALUES ('Game');

INSERT INTO categories (name_category)
VALUES ('Others');

INSERT INTO products (name_product, price_product)
VALUES ( 'MacBook Pro', 3500);

INSERT INTO products (name_product, price_product)
VALUES ('World Of Warcraft', 150);

INSERT INTO products (name_product, price_product)
VALUES ( 'HDMI Cable', 10);

INSERT INTO products (name_product, price_product)
VALUES ('Laptop Razer', 4500);

INSERT INTO products (name_product, price_product)
VALUES ('The Last of Us', 120);

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

INSERT INTO variant_products (name_variant, description_variant, link_photo, id_product)
VALUES ('MacBook Pro + Mem', 'more memory', 'macbookpro.jpeg', 1);

INSERT INTO variant_products (name_variant, description_variant, link_photo, id_product)
VALUES ('MacBook Pro + SSD', 'more SSD', 'macbookpro.jpeg', 1);

INSERT INTO customers (name_customer, email_customer, phone_number, address_customer, eircode_customer, country, city)
VALUES ('Christian', 'christian@email.com', '998776-5432', 'street 01', 'D96UZ99', 'Ireland', 'Dublin');

