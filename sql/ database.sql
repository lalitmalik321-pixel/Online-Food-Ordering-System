CREATE DATABASE food_ordering;

USE food_ordering;

CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100),
email VARCHAR(100) UNIQUE,
password VARCHAR(100)
);

CREATE TABLE menu(
id INT PRIMARY KEY AUTO_INCREMENT,
food_name VARCHAR(100),
price DECIMAL(10,2),
category VARCHAR(50)
);

INSERT INTO menu(food_name,price,category)
VALUES
('Burger',120,'Fast Food'),
('Pizza',250,'Fast Food'),
('Coffee',90,'Beverage'),
('Pasta',180,'Italian'),
('French Fries',100,'Snacks');

CREATE TABLE orders(
order_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
total DECIMAL(10,2),
order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY(user_id)
REFERENCES users(id)
);

CREATE TABLE order_items(
id INT PRIMARY KEY AUTO_INCREMENT,
order_id INT,
food_id INT,
quantity INT,
price DECIMAL(10,2),
FOREIGN KEY(order_id)
REFERENCES orders(order_id),
FOREIGN KEY(food_id)
REFERENCES menu(id)
);

