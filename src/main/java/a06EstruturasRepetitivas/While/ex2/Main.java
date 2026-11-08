package a06EstruturasRepetitivas.While.ex2;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a coordenada (para sair acerte qualquer um " +
                                 "dos eixos): ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        while (x != 0 || y != 0) {
            if (x > 0 && y > 0) {
                System.out.println("primeiro");
            }
            else if (x < 0 && y < 0) {
                System.out.println("terceiro");
            }
            else if (x < 0 && y > 0) {
                System.out.println("segundo");
            }
            else if (x > 0 && y < 0) {
                System.out.println("quarto");
            }
            x = sc.nextInt();
            y = sc.nextInt();
        }
        sc.close();
    }
}
