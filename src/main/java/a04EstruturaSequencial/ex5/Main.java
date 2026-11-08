package a04EstruturaSequencial.ex5;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o código da peça 1: ");
        int pc1 = sc.nextInt();
        System.out.print("Digite o número de peças: ");
        int pc1Qtd = sc.nextInt();
        System.out.print("Digite o valor de cada peça: ");
        double pc1Price = sc.nextDouble();

        System.out.print("Digite o código da peça 2: ");
        int pc2 = sc.nextInt();
        System.out.print("Digite o número de peças: ");
        int pc2Qtd = sc.nextInt();
        System.out.print("Digite o valor de cada peça: ");
        double pc2Price = sc.nextDouble();

        double price1 = pc1Price * pc1Qtd;
        double price2 = pc2Price * pc2Qtd;

        System.out.printf("Valor a pagar: R$%.2f", (price1 + price2));
        sc.close();
    }
}
