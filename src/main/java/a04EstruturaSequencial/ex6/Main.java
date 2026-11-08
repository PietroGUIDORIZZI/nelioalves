package a04EstruturaSequencial.ex6;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite os valores de A, B e C: ");
        double a, b , c;
        a = sc.nextDouble();
        b = sc.nextDouble();
        c = sc.nextDouble();

        double triangle = a * c / 2.0;
        double circle = Math.pow(c, 2) * Math.PI;
        double trapeze = (a + b) * c / 2.0;
        double square = Math.pow(b, 2);
        double retangle = a * b;

        System.out.printf("TRIANGULO: %.3f%n", triangle);
        System.out.printf("CIRCULO: %.3f%n", circle);
        System.out.printf("TRAPEZIO: %.3f%n", trapeze);
        System.out.printf("QUADRADO: %.3f%n", square);
        System.out.printf("RETANGULO: %.3f%n", retangle);
        sc.close();
    }
}
