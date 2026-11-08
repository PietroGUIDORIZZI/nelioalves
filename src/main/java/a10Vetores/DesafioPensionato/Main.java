package a10Vetores.DesafioPensionato;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("How many rooms will be rented? ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println();
        String[] names = new String[10];
        String[] emails = new String[10];
        Integer[] rooms = new Integer[10];

        for (int i = 0; i < n; i++) {
            System.out.println("Rent #" + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Room # ( 1 - 10 ): ");
            int room = sc.nextInt();
            sc.nextLine();
            names[room] = name;
            emails[room] = email;
            rooms[room] = room;
            System.out.println();
        }

        System.out.println("Busy rooms:");
        for (int i = 0; i < 10; i++) {
            if (rooms[i] != null) {
                System.out.println(String.format("%d: %s, %s", rooms[i],
                                                 names[i], emails[i]));
            }
        }

        sc.close();
    }
}
