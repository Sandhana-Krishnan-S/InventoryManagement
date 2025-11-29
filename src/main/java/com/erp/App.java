package com.erp;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ProductService ps = new ProductService();
        PurchaseService pur = new PurchaseService();
        SalesService ss = new SalesService();

        while (true) {
            System.out.println("\n=== Retail Inventory System ===");
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Purchase Stock");
            System.out.println("4. Sell Product");
            System.out.print("Choose: ");

            int ch = sc.nextInt();

            if (ch == 1) {
              System.out.println("\n--- Add New Product ---");
              System.out.print("Enter Product Name: ");
              String name = sc.next();
              System.out.print("Enter Price: ");
              double price = sc.nextDouble();
              System.out.print("Enter Initial Stock Quantity: ");
              int qty = sc.nextInt();
              ps.addProduct(name, price, qty);
            }

            if (ch == 2) {
              System.out.println("\n--- Product List ---");
              ps.listProducts();
            }

            if (ch == 3) {
              System.out.println("\n--- Purchase Stock (Increase Quantity) ---");
              System.out.print("Enter Product ID: ");
              int id = sc.nextInt();
              System.out.print("Enter Quantity to Add: ");
              int qty = sc.nextInt();
              pur.purchase(id, qty);
            }

            if (ch == 4) {
              System.out.println("\n--- Sell Product (Decrease Quantity) ---");
              System.out.print("Enter Product ID: ");
              int id = sc.nextInt();
              System.out.print("Enter Quantity to Sell: ");
              int qty = sc.nextInt();
              ss.sell(id, qty);
            }
        }
    }
}
