-- E-Commerce Database Initialization Script
-- Run this script after creating the database to populate initial data

USE ecommerce_db;

-- Insert Roles
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- Insert Users
INSERT INTO users (first_name, last_name, email, password, phone, enabled, created_at, updated_at) 
VALUES 
('Admin', 'User', 'admin@ecommerce.com', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy76mkm', '9999999999', 1, NOW(), NOW()),
('John', 'Doe', 'john@example.com', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy76mkm', '1234567890', 1, NOW(), NOW()),
('Jane', 'Smith', 'jane@example.com', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy76mkm', '0987654321', 1, NOW(), NOW());

-- Assign Roles to Users
INSERT INTO user_roles (user_id, role_id) 
VALUES 
(1, 2),  -- Admin User
(2, 1),  -- John - USER
(3, 1);  -- Jane - USER

-- Insert Categories
INSERT INTO categories (name, description) 
VALUES 
('Electronics', 'Electronic devices and gadgets'),
('Clothing', 'Apparel and fashion items'),
('Books', 'Books and educational materials'),
('Home & Garden', 'Home improvement and garden supplies'),
('Sports', 'Sports equipment and accessories');

-- Insert Products
INSERT INTO products (name, description, price, sku, brand, image_url, active, category_id, created_at) 
VALUES 
('Laptop Pro', 'High-performance laptop for professionals', 999.99, 'LAPTOP001', 'TechBrand', 'https://example.com/laptop.jpg', 1, 1, NOW()),
('Wireless Headphones', 'Noise-cancelling wireless headphones', 129.99, 'WH001', 'AudioBrand', 'https://example.com/headphones.jpg', 1, 1, NOW()),
('Smartphone X', 'Latest smartphone with advanced features', 799.99, 'PHONE001', 'PhoneBrand', 'https://example.com/phone.jpg', 1, 1, NOW()),
('T-Shirt', 'Comfortable cotton t-shirt', 19.99, 'TSHIRT001', 'FashionBrand', 'https://example.com/tshirt.jpg', 1, 2, NOW()),
('Running Shoes', 'Professional running shoes', 89.99, 'SHOES001', 'SportsBrand', 'https://example.com/shoes.jpg', 1, 5, NOW()),
('Programming Book', 'Learn Spring Boot from scratch', 39.99, 'BOOK001', 'TechPublisher', 'https://example.com/book.jpg', 1, 3, NOW());

-- Insert Inventory
INSERT INTO inventory (product_id, available_quantity, reserved_quantity) 
VALUES 
(1, 15, 0),
(2, 30, 0),
(3, 10, 0),
(4, 50, 0),
(5, 25, 0),
(6, 100, 0);

-- Insert Carts
INSERT INTO carts (user_id) 
VALUES 
(2),
(3);

-- Note: password for admin@ecommerce.com and other test users is 'password123'
-- The password hash above is for 'password123' using BCrypt

-- Verify data
SELECT 'Roles:' as data_type;
SELECT * FROM roles;

SELECT 'Users:' as data_type;
SELECT * FROM users;

SELECT 'User Roles:' as data_type;
SELECT * FROM user_roles;

SELECT 'Categories:' as data_type;
SELECT * FROM categories;

SELECT 'Products:' as data_type;
SELECT * FROM products;

SELECT 'Inventory:' as data_type;
SELECT * FROM inventory;
