-- Creates SCHEMA
CREATE SCHEMA PRODUCT_CRUD;

-- Create the categories table
CREATE TABLE PRODUCT_CRUD.CATEGORIES (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(255) UNIQUE NOT NULL
);

-- Create the products table with an additional name column
CREATE TABLE PRODUCT_CRUD.PRODUCTS (
                                       id UUID PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,  -- New name column
                                       description VARCHAR(255) NOT NULL,
                                       price DECIMAL(10, 2) NOT NULL,
                                       manufacturer VARCHAR(255) NOT NULL,
                                       category_id INT NOT NULL,
                                       created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       region VARCHAR(10) CHECK (region IN ('US', 'CAN')),
                                       FOREIGN KEY (category_id) REFERENCES PRODUCT_CRUD.CATEGORIES(id)
);

-- Insert sample categories
INSERT INTO PRODUCT_CRUD.CATEGORIES (name) VALUES ('Electronics');
INSERT INTO PRODUCT_CRUD.CATEGORIES (name) VALUES ('Clothing');
INSERT INTO PRODUCT_CRUD.CATEGORIES (name) VALUES ('Home & Garden');
INSERT INTO PRODUCT_CRUD.CATEGORIES (name) VALUES ('Sports');
INSERT INTO PRODUCT_CRUD.CATEGORIES (name) VALUES ('Toys');

-- Insert sample products with the new name column
INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Smartphone', 'Latest model smartphone', 699.99, 'TechCorp', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Laptop', 'High-performance laptop', 999.99, 'CompuWorld', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Bluetooth Headphones', 'Noise-cancelling headphones', 199.99, 'AudioTech', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'CAN');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Smartwatch', 'Smartwatch with fitness tracking', 249.99, 'WearableTech', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), '4K TV', 'Ultra HD Smart TV', 1299.99, 'Visionary', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'CAN');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'T-Shirt', 'Cotton T-Shirt', 19.99, 'FashionCo', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Clothing'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Jeans', 'Denim jeans', 49.99, 'DenimWorld', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Clothing'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Jacket', 'Winter jacket', 89.99, 'Outerwear Inc.', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Clothing'), 'CAN');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Sneakers', 'Comfortable sneakers', 69.99, 'Footwear Co.', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Clothing'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Lawn Mower', 'Gas-powered lawn mower', 299.99, 'GardenPro', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Home & Garden'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Smartphone', 'Latest model smartphone', 699.99, 'TechCorp', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Laptop', 'High-performance laptop', 999.99, 'CompuWorld', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Bluetooth Headphones', 'Noise-cancelling headphones', 199.99, 'AudioTech', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'CAN');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Smartwatch', 'Smartwatch with fitness tracking', 249.99, 'WearableTech', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), '4K TV', 'Ultra HD Smart TV', 1299.99, 'Visionary', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Electronics'), 'CAN');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'T-Shirt', 'Cotton T-Shirt', 19.99, 'FashionCo', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Clothing'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Jeans', 'Denim jeans', 49.99, 'DenimWorld', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Clothing'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Jacket', 'Winter jacket', 89.99, 'Outerwear Inc.', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Clothing'), 'CAN');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Sneakers', 'Comfortable sneakers', 69.99, 'Footwear Co.', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Clothing'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Lawn Mower', 'Gas-powered lawn mower', 299.99, 'GardenPro', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Home & Garden'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Garden Rake', 'Heavy-duty garden rake', 25.99, 'GardenPro', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Home & Garden'), 'CAN');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Patio Set', 'Outdoor patio furniture set', 499.99, 'Outdoor Living', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Home & Garden'), 'US');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Grill', 'Charcoal grill for outdoor cooking', 299.99, 'BBQ Masters', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Home & Garden'), 'CAN');

INSERT INTO PRODUCT_CRUD.PRODUCTS (id, name, description, price, manufacturer, category_id, region)
VALUES (UUID(), 'Flower Pot', 'Ceramic flower pot', 15.99, 'Garden Essentials', (SELECT id FROM PRODUCT_CRUD.CATEGORIES WHERE name = 'Home & Garden'), 'US');
