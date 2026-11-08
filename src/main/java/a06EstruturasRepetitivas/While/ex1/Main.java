package a06EstruturasRepetitivas.While.ex1;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite sua senha: ");
        int senha = sc.nextInt();

        while (senha != 2002) {
            System.out.println("Senha Invalida");
            senha = sc.nextInt();

        }
        System.out.println("Acesso Permitido");
        sc.close();
    }
}
