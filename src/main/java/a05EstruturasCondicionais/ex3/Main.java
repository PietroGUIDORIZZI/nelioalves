package a05EstruturasCondicionais.ex3;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite dois número para testar se são múltiplos " +
                                 "entre sí: ");
        int a = sc.nextInt();
        int b = sc.nextInt();

        String multiplos = (a % b == 0 || b % a == 0) ? "Sao Multiplos" :
                           "Nao sao Multiplos";
        System.out.println(multiplos);

    }
}
