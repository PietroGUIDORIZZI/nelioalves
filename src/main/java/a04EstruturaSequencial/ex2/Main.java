package a04EstruturaSequencial.ex2;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cálculo da área do círculo: ");
        System.out.print("Digite o raio do círculo: ");
        double raio = sc.nextDouble();
        double area = Math.pow(raio, 2) * Math.PI;
        System.out.printf("A=%.4f", area);
        sc.close();
    }
}
