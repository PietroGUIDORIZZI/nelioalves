package a10Vetores.somaVetor;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantos numeros voce vai digita? ");
        int n = sc.nextInt();
        double nums[] = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Digite um numero: ");
            nums[i] = sc.nextDouble();
        }
        imprimir(nums);
        imprimirSoma(nums);
        imprimirMedia(nums);


        sc.close();
    }

    public static void imprimir(double nums[]) {
        StringBuilder sb = new StringBuilder();
        sb.append("VALORES = ");
        for (int i = 0; i < nums.length - 1 ; i++) {
            sb.append(String.format("%.1f  ", nums[i]));
        }
        sb.append(String.format("%.1f", nums[nums.length - 1]));
        System.out.println(sb.toString());
    }

    public static void imprimirSoma(double nums[]) {
        double soma = 0;
        for (int i = 0; i < nums.length; i++) {
            soma += nums[i];
        }
        System.out.println(String.format("SOMA = %.2f", soma));
    }

    public static void imprimirMedia(double nums[]) {
        double soma = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            soma += nums[i];
            count++;
        }
        System.out.println(String.format("MEDIA = %.2f", soma / count));

    }
}
