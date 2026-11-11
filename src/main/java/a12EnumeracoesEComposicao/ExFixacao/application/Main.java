package a12EnumeracoesEComposicao.ExFixacao.application;

import a12EnumeracoesEComposicao.ExFixacao.entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter client data:");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        String dob = sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = sdf.parse(dob);
        Client client = new Client(name, email, birthDate);

        System.out.println("Enter order data:");
        System.out.print("Status [PENDING_PAYMENT(0)," +
                                 "    PROCESSING(1)," +
                                 "    SHIPPED(2)," +
                                 "    DELIVERED(3)]: ");
        String status = sc.nextLine();
        int st = Integer.parseInt(status);
        Order order = new Order(new Date(), OrderStatus.values()[st],
                                client);

        

        System.out.print("How many items to this order? ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n ; i++) {
            System.out.println("Enter #" + (i + 1) + " item data:");
            System.out.print("Product name: ");
            String productName = sc.nextLine();
            System.out.print("Product price: ");
            Double productPrice = sc.nextDouble();
            System.out.print("Quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();
            Product product = new Product(productName, productPrice) ;
            OrderItem orderItem = new OrderItem(quantity, productPrice, order
                    , product);
            order.addItem(orderItem);
        }
        System.out.println();
        System.out.println("ORDER SUMMARY:");
        System.out.println(order);

        sc.close();
    }
}
