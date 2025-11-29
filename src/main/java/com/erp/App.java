package com.erp;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ProductService ps = new ProductService();
        PurchaseService pur = new PurchaseService();
        SalesService ss = new SalesService();

        while (true) {
            System.out.println("\n=== ERP Inventory System ===");
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Purchase Stock");
            System.out.println("4. Sell Product");
            System.out.print("Choose: ");

            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Price: ");
                double price = sc.nextDouble();
                System.out.print("Qty: ");
                int qty = sc.nextInt();
                ps.addProduct(name, price, qty);
            }

            if (ch == 2) {
                ps.listProducts();
            }

            if (ch == 3) {
                System.out.print("Product ID: ");
                int id = sc.nextInt();
                System.out.print("Qty: ");
                int qty = sc.nextInt();
                pur.purchase(id, qty);
            }

            if (ch == 4) {
                System.out.print("Product ID: ");
                int id = sc.nextInt();
                System.out.print("Qty: ");
                int qty = sc.nextInt();
                ss.sell(id, qty);
            }
        }
    }
}
