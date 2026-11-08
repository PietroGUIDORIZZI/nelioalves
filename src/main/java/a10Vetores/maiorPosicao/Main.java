package a10Vetores.maiorPosicao;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantos numeros voce vai digitar? ");
        int n = Integer.parseInt(sc.nextLine());
        double nums[] = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Digite um numero: ");
            nums[i] = sc.nextDouble();
        }
        double maior = nums[0];
        int pos = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maior) {
                maior = nums[i];
                pos = i;
            }
        }
        System.out.println(String.format("MAIOR VALOR = %.1f", maior));
        System.out.println(String.format("POSICAO DO MAIOR VALOR = %d",
                                         pos));


        sc.close();
    }
}
