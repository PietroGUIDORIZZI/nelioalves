package a05EstruturasCondicionais.ex7;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a coordenada (x, y):");
        double x = sc.nextDouble();
        double y = sc.nextDouble();

        if (x == 0 && y == 0) {
            System.out.println("Origem");
        } else if (x > 0 && y > 0) {
            System.out.println("Q1");
        } else if (x < 0 && y < 0) {
            System.out.println("Q2");
        } else if (x < 0 && y > 0) {
            System.out.println("Q3");
        } else if (x > 0 && y < 0) {
            System.out.println("Q4");
        } else if (x == 0){
            System.out.println("Eixo Y");
        } else {
            System.out.println("Eixo X");
        }
    }
}
