package a13HerancaEPolimorfismo.exFixacao144.application;

import a13HerancaEPolimorfismo.exFixacao144.entities.ImportedProduct;
import a13HerancaEPolimorfismo.exFixacao144.entities.Product;
import a13HerancaEPolimorfismo.exFixacao144.entities.UsedProduct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of products: ");
        int n = Integer.parseInt(sc.nextLine());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Product #" + (i + 1) + " data:");
            System.out.print("Common, used or imported (c/u/i)? ");
            String ans = sc.nextLine();
            char answer = ans.charAt(0);
            if (answer == 'c') {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Price: ");
                double price = Double.parseDouble(sc.nextLine());
                products.add(new Product(price, name));
            }
            else if (answer == 'i') {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Price: ");
                double price = Double.parseDouble(sc.nextLine());
                System.out.print("Customs fee: ");
                double customsFee = Double.parseDouble(sc.nextLine());
                products.add(new ImportedProduct(price, name, customsFee));
            } else{
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Price: ");
                double price = Double.parseDouble(sc.nextLine());
                System.out.print("Manufacture date (DD/MM/YYYY): ");
                String mdate = sc.nextLine();
                Date manufactureDate = sdf.parse(mdate);
                products.add(new UsedProduct(price, name, manufactureDate));
            }
        }
        System.out.println();
        System.out.println("PRICE TAGS:");
        for (Product p : products) {
            System.out.println(p.priceTag());
        }

        sc.close();
    }
}
