package a05EstruturasCondicionais.ex1;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número para testar se é negativo ou não: ");
        int n = sc.nextInt();
        String negativo = (n < 0) ? "NEGATIVO" : "NAO NEGATIVO";
        System.out.println(negativo);
        sc.close();
    }
}
