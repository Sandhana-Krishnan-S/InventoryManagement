### Retail Inventory System Console Application (Java + Maven + MySQL)

A simple ERP-style Inventory Management System built with:

Java (JDK 8+)

JDBC (MySQL)

# Maven

# Console-based UI

This project manages products, purchases, and sales with persistent storage in MySQL.

## Features

1. Add Product – name, price, quantity

2. List Products – see ID, name, price, stock

3. Purchase Stock – increases quantity

4. Sell Product – decreases quantity

Persistent storage using MySQL tables

Straightforward service classes (ProductService, PurchaseService, SalesService)

## Project Structure
```
erp-console/
 ├── pom.xml
 ├── src/
 │   └── main/
 │       └── java/
 │            └── com/erp/
 │                 ├── App.java
 │                 ├── DB.java
 │                 ├── ProductService.java
 │                 ├── PurchaseService.java
 │                 └── SalesService.java
 └── README.md
```
## Database Setup (MySQL)

Run these SQL commands:
```sql
CREATE DATABASE IF NOT EXISTS erp;
USE erp;

CREATE TABLE IF NOT EXISTS products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  price DOUBLE,
  quantity INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS purchases (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_id INT,
  quantity INT,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS sales (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_id INT,
  quantity INT,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
```

Configure Database Credentials

Edit DB.java:
```java
private static final String USER = "root";
private static final String PASS = "yourpassword";
```

Make sure the database erp exists and your credentials match.

How to Run
Compile:
```
mvn clean compile
```
Run:
```
mvn exec:java -Dexec.mainClass="com.erp.App"
```
How It Works

User selects actions from a text menu:

1. Add Product
2. List Products
3. Purchase Stock
4. Sell Product
5. Exit


Each action calls a service class:

ProductService

PurchaseService

SalesService

JDBC is used to execute SQL queries directly on MySQL.
