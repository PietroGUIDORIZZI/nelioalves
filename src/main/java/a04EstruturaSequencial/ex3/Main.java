package a04EstruturaSequencial.ex3;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite os valores de A, B, C e D: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        System.out.println("DIFERENCA = " + (a * b - c * d));
        sc.close();
    }
}
