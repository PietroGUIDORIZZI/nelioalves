package a05EstruturasCondicionais.ex2;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número para testar se é par ou ímpar: ");
        int n = sc.nextInt();
        String parOuImpar = (n % 2 == 0) ? "PAR" : "IMPAR";
        System.out.println(parOuImpar);
        
    }
}
